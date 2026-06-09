package mdtg.business.attachment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import mdtg.business.attachment.dto.QueryAttachmentInputDTO;
import mdtg.business.attachment.entity.Attach;
import mdtg.business.attachment.mapper.AttachMapper;
import mdtg.business.attachment.service.AttachService;
import mdtg.business.common.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author WangYunwei
 */
@Service
public class AttachServiceImpl extends ServiceImpl<AttachMapper, Attach> implements AttachService {

    private final MinioClient minioClient;

    public AttachServiceImpl(MinioClient minioClient) {

        this.minioClient = minioClient;
    }

    @Override
    public ResponseDTO<?> addAttachment(Attach inputDTO) {

        if (inputDTO.getId() != null && inputDTO.getId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.updateById(inputDTO));
        }
        return ResponseDTO.wrapSuccess(this.baseMapper.insert(inputDTO));
    }

    @Override
    public ResponseDTO<?> deleteAttachment(Long id) {

        return ResponseDTO.wrapSuccess(this.baseMapper.deleteById(id));
    }

    @Override
    public ResponseDTO<?> queryAttachment(QueryAttachmentInputDTO inputDTO) {

        if (inputDTO.getAttachId() != null && inputDTO.getAttachId() > 0) {
            return ResponseDTO.wrapSuccess(this.baseMapper.selectById(inputDTO.getAttachId()));
        }
        LambdaQueryWrapper<Attach> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(inputDTO.getBusinessId()).ifPresent(businessId -> queryWrapper.eq(Attach::getBusinessId, businessId));
        Optional.ofNullable(inputDTO.getBusinessType()).ifPresent(businessType -> queryWrapper.eq(Attach::getBusinessType, businessType));
        Optional.ofNullable(inputDTO.getContentType()).ifPresent(contentType -> queryWrapper.eq(Attach::getContentType, contentType));
        Optional.ofNullable(inputDTO.getFileName()).ifPresent(fileName -> queryWrapper.like(Attach::getFileName, fileName));
        IPage<Attach> page = new Page<>(inputDTO.getPageNum(), inputDTO.getPageSize());
        return ResponseDTO.wrapSuccess(this.baseMapper.selectPage(page, queryWrapper));
    }

    @Override
    public ResponseDTO<?> uploadFiles(MultipartFile[] files, String name, String deviceId) {

        return null;
    }
}




