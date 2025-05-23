package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.FileDto;
import kg.attractor.microgram.dto.LikeDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.File;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.FileRepository;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserService userService;
    private final FileUtil fileUtil;

    @Override
    public List<FileDto> findAllFiles(){
        List<File> files = fileRepository.findAll();
        return files.stream().map(e -> FileDto.builder()
                .id(e.getId())
                .description(e.getDescription())
                .dateTimePublished(e.getDateTimePublished())
                .fileName(e.getFileName())
                .comments(e.getComments().stream().map(c -> CommentDto.builder()
                        .id(c.getId())
                        .comment(c.getComment())
                        .dateTimePublished(c.getDateTimePublished())
                        .userDto(UserDto.builder()
                                .email(c.getUser().getEmail())
                                .build())
                        .build()).toList())
                .likes(e.getLikes().stream().map(l -> LikeDto.builder()
                        .id(l.getId())
                        .userDto(UserDto.builder()
                                .email(l.getUser().getEmail())
                                .build())
                        .build()).toList())
                .userDto(userService.findByEmail(e.getUser().getEmail()))
                .build()).toList();
    }

    @Override
    public FileDto findByNameDto(String fileName){
        File file = fileRepository.findByFileName(fileName);
        return FileDto.builder()
                .id(file.getId())
                .description(file.getDescription())
                .dateTimePublished(file.getDateTimePublished())
                .fileName(file.getFileName())
                .build();
    }

    @Override
    public ResponseEntity<?> findByName(String imageName) {
        return fileUtil.getOutputFile(imageName, "images/", MediaType.IMAGE_JPEG);
    }

    @Override
    public void uploadFile(FileDto fileDto){
        String fileName = fileUtil.saveUploadFile(fileDto.getMultipartFile(), "images/");
        User user = userService.findByEmailModel(fileDto.getUserDto().getEmail());

        File file = new File();
        file.setUser(user);
        file.setFileName(fileName);
        file.setDateTimePublished(LocalDateTime.now());
        file.setDescription(fileDto.getDescription());

        fileRepository.saveAndFlush(file);
    }
}
