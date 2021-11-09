package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(CloudinaryConfig.class)
@ConfigurationProperties(prefix = "application.cloudinary")
@NoArgsConstructor
@Getter
@Setter
@Configuration
public class CloudinaryConfig {
    private String cloud_name;
    private String api_key;
    private String api_secret;

    public Cloudinary getCloudinary() {
        Map<Object, Object> config = new HashMap<Object, Object>(ObjectUtils.asMap(
            "cloud_name", cloud_name,
            "api_key", api_key,
            "api_secret", api_secret
        ));
        return new Cloudinary(config);
    }
}
