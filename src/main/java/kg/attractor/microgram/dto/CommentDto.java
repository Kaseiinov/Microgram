package kg.attractor.microgram.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    @NotBlank
    private String comment;
    private UserDto userDto;
    private FileDto fileDto;
    private LocalDateTime dateTimePublished;
}
