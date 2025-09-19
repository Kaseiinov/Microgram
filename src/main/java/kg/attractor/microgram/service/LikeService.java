package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.LikeDto;

public interface LikeService {
    void saveOrDeleteLike(LikeDto likeDto);
}
