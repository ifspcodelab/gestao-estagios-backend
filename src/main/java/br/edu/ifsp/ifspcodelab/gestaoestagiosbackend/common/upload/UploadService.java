package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.FileMaxSizeException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.FileUploadException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.ParameterService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.FileExtensionPdfException;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@Service
public class UploadService {
    private final CloudinaryConfig cloudinaryConfig;
    private ParameterService parameterService;

    public UploadService(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Autowired
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public String uploadFile(MultipartFile file, String fileName) {
        try {
            Map uploadResult = cloudinaryConfig
                .getCloudinary()
                .uploader()
                .upload(file.getBytes(), ObjectUtils.asMap("public_id", fileName)
                );
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new FileUploadException("Error sending file: " + file.getName());
        }
    }

    public void pdfValidation(MultipartFile file) {
        if (!Objects.requireNonNull(file.getContentType()).equals(MediaType.APPLICATION_PDF_VALUE)) {
            throw new FileExtensionPdfException(MediaType.APPLICATION_PDF_VALUE, file.getContentType());
        }
    }

    public void activityPlanFileValidation(MultipartFile file) {
        pdfValidation(file);
        if (file.getSize() > parameterService.findFirst().getActivityPlanFileSizeMegabytes() * 1048576) {
            throw new FileMaxSizeException(
                parameterService.findFirst().getActivityPlanFileSizeMegabytes() * 1048576,
                file.getSize()
            );
        }
    }

    public void monthlyReportFileValidation(MultipartFile file) {
        pdfValidation(file);
        if (file.getSize() > parameterService.findFirst().getMonthlyReportFileSizeMegabytes() * 1048576) {
            throw new FileMaxSizeException(
                parameterService.findFirst().getMonthlyReportFileSizeMegabytes() * 1048576,
                file.getSize()
            );
        }
    }
}
