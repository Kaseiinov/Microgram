package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private Long id;
    @NotNull
    private MultipartFile multipartFile;
    private String fileName;
    private String description;
    private UserDto userDto;
    private LocalDateTime dateTimePublished;
    private List<CommentDto> comments;
    private List<LikeDto> likes;
}
