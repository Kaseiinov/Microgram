package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.FileDto;
import kg.attractor.microgram.model.File;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FileService {
    List<FileDto> findAllFilesByUser(String email);

    List<FileDto> findAllFiles();

    File findByNameModel(String fileName);

    FileDto findByNameDto(String fileName);

    ResponseEntity<?> findByName(String imageName);

    void uploadFile(FileDto fileDto);
}
