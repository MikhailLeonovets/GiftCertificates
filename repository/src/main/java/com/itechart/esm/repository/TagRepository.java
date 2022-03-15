package com.itechart.esm.repository;

import com.itechart.esm.repository.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

	Tag save(Tag tag);

	List<Tag> findAll();

	Optional<Tag> findById(Long id);

	void delete(Tag tag);

	void deleteById(Long id);

}
