package com.reason.application.springboot.service;

import com.reason.application.springboot.domain.posts.Posts;
import com.reason.application.springboot.domain.posts.PostsRepository;
import com.reason.application.springboot.web.dto.PostsListResponseDto;
import com.reason.application.springboot.web.dto.PostsResponseDto;
import com.reason.application.springboot.web.dto.PostsSaveRequestDto;
import com.reason.application.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    } // 트랜잭션 내부에서 데이터를 가져오면 영속성 컨텍스트 유지 상태가 됨
    // 트랜잭션 끝날 때 이 데이터에 변경이 일어나면 자동으로 DB반영

    @Transactional(readOnly = true)
    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id"+ id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    } // JPA를 활용한 엔티티 조회 후 삭제 (deleteById 도 가능)
}
