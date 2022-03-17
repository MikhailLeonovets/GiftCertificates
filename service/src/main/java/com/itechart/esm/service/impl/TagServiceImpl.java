package com.itechart.esm.service.impl;

import com.itechart.esm.repository.TagRepository;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.TagService;
import com.itechart.esm.service.exception.TagNotFoundException;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {
	private final TagRepository tagRepository;

	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public void save(Tag tag) {
		tagRepository.save(tag);
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag findById(Long id) throws TagNotFoundException {
		Optional<Tag> optionalTag = tagRepository.findById(id);
		if (optionalTag.isEmpty()) {
			throw new TagNotFoundException();
		}
		return null;
	}

	@Override
	public void delete(Tag tag) throws TagNotFoundException {
		boolean isDeleted = tagRepository.delete(tag);
		if (!isDeleted) {
			throw new TagNotFoundException();
		}
	}

	@Override
	public void deleteById(Long id) throws TagNotFoundException {
		boolean isDeleted = tagRepository.deleteById(id);
		if (!isDeleted) {
			throw new TagNotFoundException();
		}
	}
}
