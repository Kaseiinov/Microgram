package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.FileDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FileService {
    List<FileDto> findAllFiles();

    ResponseEntity<?> findByName(String imageName);

    void uploadFile(FileDto fileDto);
}
