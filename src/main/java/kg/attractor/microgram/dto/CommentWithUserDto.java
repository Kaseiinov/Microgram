package kg.attractor.microgram.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentWithUserDto {
    private String email;
    private String comment;

}

