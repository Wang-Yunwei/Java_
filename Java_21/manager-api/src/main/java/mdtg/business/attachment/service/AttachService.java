package mdtg.business.attachment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import mdtg.business.attachment.dto.AddAttachmentInputDTO;
import mdtg.business.attachment.dto.QueryAttachmentInputDTO;
import mdtg.business.attachment.entity.Attach;
import mdtg.business.common.toolkits.ResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author WangYunwei
 */
public interface AttachService extends IService<Attach> {

    ResponseDTO<?> addAttachment(Attach inputDTO);

    ResponseDTO<?> deleteAttachment(String attachId);

    ResponseDTO<?> queryAttachment(QueryAttachmentInputDTO inputDTO);
}
