package com.lookable.service.tag;

import com.lookable.domain.tag.Tag;
import com.lookable.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Tag> findOrCreateTags(List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();

        tagNames.forEach(name -> tagRepository.findByName(name)
                .ifPresentOrElse(
                        tags::add,
                        () -> tags.add(createTag(name))
                ));

        return tags;
    }

    @Transactional
    public Tag findOrCreateTag(String tagName) {
        return tagRepository.findByName(tagName)
                .orElseGet(() -> createTag(tagName));
    }

    @Transactional
    public Tag createTag(String name) {
        Tag tag = Tag.builder()
                .name(name)
                .build();
        return tagRepository.save(tag);
    }

}
