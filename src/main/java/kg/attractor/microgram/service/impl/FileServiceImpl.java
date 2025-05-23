package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.FileDto;
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
                .build()).toList();
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
