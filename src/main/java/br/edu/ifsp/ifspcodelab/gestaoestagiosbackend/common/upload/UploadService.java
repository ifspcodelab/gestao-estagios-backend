package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload;

import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@AllArgsConstructor
@Service
public class UploadService {
    private final CloudinaryConfig cloudinaryConfig;

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            Map uploadResult = cloudinaryConfig
                .getCloudinary()
                .uploader()
                .upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName)
                );
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
