package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.LikeDto;
import kg.attractor.microgram.model.Like;
import kg.attractor.microgram.repository.LIkeRepository;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final FileService fileService;
    private final LIkeRepository likeRepository;
    private final UserService userService;

    @Override
    public void saveOrDeleteLike(LikeDto likeDto) {
        Optional<Like> existingLike = likeRepository.findByFile_FileNameAndUser_Email(
                likeDto.getFileDto().getFileName(),
                likeDto.getUserDto().getEmail()
        );

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return;
        }

        Like like = new Like();
        like.setFile(fileService.findByNameModel(likeDto.getFileDto().getFileName()));
        like.setUser(userService.findByEmailModel(likeDto.getUserDto().getEmail()));
        likeRepository.saveAndFlush(like);
    }


}
