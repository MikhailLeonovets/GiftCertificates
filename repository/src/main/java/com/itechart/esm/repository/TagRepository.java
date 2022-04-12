package com.itechart.esm.repository;

import com.itechart.esm.repository.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

	/**
	 *
	 * @param tag is to be saved to the database
	 * @return
	 */
	Tag save(Tag tag);

	List<Tag> findAll();

	Optional<Tag> findById(Long id);

	Optional<Tag> findByName(String name);

	boolean delete(Tag tag);

	boolean deleteById(Long id);

	void deleteAll();

}
