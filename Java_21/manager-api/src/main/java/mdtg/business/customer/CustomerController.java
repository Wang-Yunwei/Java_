package mdtg.business.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mdtg.business.common.toolkits.ResponseDTO;
import mdtg.business.customer.dto.*;
import mdtg.business.customer.feign.CustomerFeign;
import mdtg.common.utils.Result;
import mdtg.modules.knowledge.dto.KnowledgeBaseDTO;
import mdtg.modules.knowledge.service.KnowledgeBaseService;
import mdtg.modules.voiceclone.dto.VoiceCloneDTO;
import mdtg.modules.voiceclone.entity.VoiceCloneEntity;
import mdtg.modules.voiceclone.service.VoiceCloneService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * @author WangYunwei [2026-03-31]
 */
@Tag(name = "MDTG - 用户端 API")
@RequestMapping("/customer-service")
@RestController
public class CustomerController {

    private final CustomerFeign customerFeign;

    private final VoiceCloneService voiceCloneService;

    private final KnowledgeBaseService knowledgeBaseService;

    private final HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper om = new ObjectMapper();

    public CustomerController(CustomerFeign customerFeign, VoiceCloneService voiceCloneService, KnowledgeBaseService knowledgeBaseService) {

        this.customerFeign = customerFeign;

        this.voiceCloneService = voiceCloneService;

        this.knowledgeBaseService = knowledgeBaseService;
    }

    @Operation(summary = "声音克隆 - 查询")
    @PostMapping("/voice-clone/query")
    public ResponseDTO<?> queryVoice(@RequestHeader("Authorization") String token, @RequestBody VoiceClonePageDTO inputDTO) {

        Result page = customerFeign.voiceClonePage(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "声音克隆 - 更新")
    @PostMapping("/voice-clone/update-voiceId")
    public ResponseDTO<?> updateVoiceId(@RequestHeader("Authorization") String token, @RequestBody UpdateVoiceIdDTO inputDTO) {

        Result page = customerFeign.updateVoiceId(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "知识库 - 查询")
    @PostMapping("/knowledge/query")
    public ResponseDTO<?> queryKnowledge(@RequestHeader("Authorization") String token, @RequestBody KnowledgePageDTO inputDTO) {

        Result page = customerFeign.knowledgePage(token, inputDTO);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "附件 - 查询")
    @PostMapping("/attach/query")
    public ResponseDTO<?> queryAttach(@RequestHeader("Authorization") String token, @RequestBody AttachPageDTO inputDTO) {

        Result<Object> result = customerFeign.attachPage(token, inputDTO);
        return ResponseDTO.wrapSuccess(result.getData());
    }

    @Operation(summary = "附件 - 更新")
    @PostMapping("/attach/update-status")
    public ResponseDTO<?> updateAttach(@RequestHeader("Authorization") String token, @RequestBody UpdateAttachInputDTO inputDTO) {

        if (inputDTO.getAttachStatus() == 4) {
            if (inputDTO.getKnowledgeBaseInfo() != null) {
                // 1.根据信息在管理端新建知识库
                KnowledgeBaseDTO dto = new KnowledgeBaseDTO();
                BeanUtils.copyProperties(inputDTO.getKnowledgeBaseInfo(), dto, "id");
                knowledgeBaseService.save(dto);
                Result<?> result = customerFeign.updateDatasetId(token, new UpdateDatasetIdDTO().setId(inputDTO.getKnowledgeBaseInfo().getId()).setDatasetId(dto.getDatasetId()));
                if (result.getCode() == 0) {
                    Result<Object> attachPage = customerFeign.attachPage(token, new AttachPageDTO().setBusinessId(inputDTO.getKnowledgeBaseInfo().getId()));
                    if (attachPage.getCode() == 0) {
                        // 2.通过 HttpClient 获取文件流
                        JsonNode jsonNode = om.convertValue(attachPage.getData(), JsonNode.class);
                        jsonNode.get("records").forEach(attach -> {
                            Result<Map<String, String>> fileInfo = customerFeign.fileInfo(attach.get("fileName").asText());
                            if (fileInfo.getCode() == 0) {

//                                StatObjectResponse stat = minioClient.statObject(
//                                        io.minio.StatObjectArgs.builder().bucket(fileInfo.getData().get("bucketName")).object(fileInfo.getData().get("objectName")).build()
//                                );
                            }
                        });
                    }

                    // 开始上传文档到 RAGFlow

                }
            }
            if (inputDTO.getVoiceCloneInfo() != null) {
                // 1.根据信息在管理端新建声音克隆
                VoiceCloneDTO voiceCloneDTO = new VoiceCloneDTO();
                voiceCloneDTO.setModelId(inputDTO.getVoiceCloneInfo().getModelId());
                voiceCloneDTO.setUserId(inputDTO.getVoiceCloneInfo().getOrgCode());
                voiceCloneDTO.setVoiceIds(inputDTO.getVoiceCloneInfo().getVoiceIds());
                VoiceCloneEntity save = voiceCloneService.save(voiceCloneDTO);
                // 通过 HttpClient 获取文件流
                Result<Map<String, String>> result = customerFeign.fileInfo(inputDTO.getVoiceCloneInfo().getVoiceFiles().getFirst().getFileName());
                if (result.getCode() == 0) {
                    // 2. 构建请求
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(result.getData().get("fileUrl")))
                            .GET() // 默认就是 GET,可省略
                            .build();
                    // 3. 发送请求并获取响应
                    try {
                        byte[] body = client.send(request, HttpResponse.BodyHandlers.ofByteArray()).body();
                        // 4. 将文件流保存到数据库
                        VoiceCloneEntity cloneEntity = new VoiceCloneEntity();
                        cloneEntity.setId(save.getId());
                        cloneEntity.setVoice(body);
                        voiceCloneService.updateById(cloneEntity);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 2.调用声音复刻接口进行声音克隆
                voiceCloneService.cloneAudio(save.getId());
                VoiceCloneEntity entity = voiceCloneService.selectById(save.getId());
                // 3.更新用户表声音 ID
                customerFeign.updateVoiceId(token, new UpdateVoiceIdDTO().setId(inputDTO.getVoiceCloneInfo().getId()).setVoiceId(entity.getVoiceId()));
            }
        }
        AttachUpdateStatusDTO dto = new AttachUpdateStatusDTO().setId(inputDTO.getAttachId()).setStatus(inputDTO.getAttachStatus());
        Result page = customerFeign.updateAttach(token, dto);
        return ResponseDTO.wrapSuccess(page.getData());
    }

    @Operation(summary = "附件 - 下载链接")
    @GetMapping("/attach/dow-link")
    public ResponseDTO<?> attachInfo(@RequestParam String fileName) {

        Result<Map<String, String>> result = customerFeign.fileInfo(fileName);
        return ResponseDTO.wrapSuccess(result.getData());
    }
}
