package com.lookable.service.tag;

import com.lookable.domain.tag.Tag;
import com.lookable.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public List<Tag> findOrCreateTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();

        tagNames.forEach(name -> tagRepository.findByName(name)
                .ifPresentOrElse(
                        tags::add,
                        () -> tags.add(createTag(name))
                ));

        return tags;
    }

    private Tag createTag(String name) {
        Tag tag = Tag.builder()
                .name(name)
                .build();
        return tagRepository.save(tag);
    }

}
