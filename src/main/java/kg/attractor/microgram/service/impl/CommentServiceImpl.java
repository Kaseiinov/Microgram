package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserImageDto;
import kg.attractor.microgram.model.Comment;
import kg.attractor.microgram.model.File;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.model.UserImage;
import kg.attractor.microgram.repository.CommentRepository;
import kg.attractor.microgram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public void save(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());

        comment.setFile(File.builder()
                .id(commentDto.getFileDto().getId())
                .fileName(commentDto.getFileDto().getFileName())
                .description(commentDto.getFileDto().getDescription())
                .dateTimePublished(commentDto.getFileDto().getDateTimePublished())
                .build());

        User user = User.builder()
                .id(commentDto.getUserDto().getId())
                .email(commentDto.getUserDto().getEmail())
                .description(commentDto.getUserDto().getDescription())
                .avatar(UserImage.builder()
                        .id(commentDto.getUserDto().getAvatar().getId())
                        .fileName(commentDto.getUserDto().getAvatar().getFileName())
                        .build())
                .firstName(commentDto.getUserDto().getFirstName())
                .lastName(commentDto.getUserDto().getLastName())
                .password(commentDto.getUserDto().getPassword())
                .build();

        comment.setUser(user);

        comment.setDateTimePublished(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);
    }

//    @Override
//    public List<CommentDto> findAllComments(){
//        List<Comment> comments = commentRepository.findAll();
//
//        return comments.stream().map(e -> CommentDto.builder()
//                .id(e.getId())
//                .comment(e.getComment())
//                .dateTimePublished(e.getDateTimePublished())
//                .fileDto(e.getFile().getFileName())
//                .build()).toList();
//
//    }
}
