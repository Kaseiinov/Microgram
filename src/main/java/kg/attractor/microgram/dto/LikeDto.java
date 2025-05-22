package kg.attractor.microgram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDto {
    private Long id;
    private UserDto userDto;
    private FileDto fileDto;
}
