package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class FileDto {
    private Long id;
    private MultipartFile multipartFile;
    private String description;
    private UserDto userDto;
    private LocalDateTime dateTimePublished;
}
