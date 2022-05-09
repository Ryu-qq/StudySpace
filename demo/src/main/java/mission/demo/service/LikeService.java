package mission.demo.service;

import lombok.RequiredArgsConstructor;
import mission.demo.api.dto.LikeResponseDto;
import mission.demo.domain.Likes;
import mission.demo.domain.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;




}
