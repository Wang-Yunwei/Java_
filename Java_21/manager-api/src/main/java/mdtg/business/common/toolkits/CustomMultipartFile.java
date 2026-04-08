package mdtg.business.common.toolkits;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author WangYunwei [2026-04-07]
 */
public class CustomMultipartFile implements MultipartFile {

    private final InputStream inputStream;

    /**
     * 表单字段名
     */
    private final String name;

    /**
     * 原始文件名
     */
    private final String originalFilename;

    /**
     * 文件类型
     */
    private final String contentType;

    /**
     * 文件大小（可选，若未知可设为 -1）
     */
    private final long size;

    public CustomMultipartFile(InputStream inputStream, String name, String originalFilename, String contentType, long size) {

        this.inputStream = inputStream;
        this.name = name;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String getOriginalFilename() {

        return originalFilename;
    }

    @Override
    public String getContentType() {

        return contentType;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public long getSize() {

        return size;
    }

    /**
     * ⚠️ 警告：只有当文件很小时才调用此方法，否则会 OOM
     * 建议下游接口使用 getInputStream() 或 transferTo() 来处理大文件
     */
    @Override
    public byte[] getBytes() throws IOException {

        return inputStream.readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {

        return inputStream;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        // 将流直接写入目标文件，不经过内存
        java.nio.file.Files.copy(inputStream, dest.toPath());
    }
}
