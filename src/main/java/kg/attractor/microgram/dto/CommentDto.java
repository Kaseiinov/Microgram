package kg.attractor.microgram.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String comment;
    private UserDto userDto;
    private FileDto fileDto;
    private LocalDateTime dateTimePublished;
}
