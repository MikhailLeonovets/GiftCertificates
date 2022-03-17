package com.itechart.esm.service;

import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.exception.TagNotFoundException;

import java.util.List;

public interface TagService {

	void save(Tag tag);

	List<Tag> findAll();

	Tag findById(Long id) throws TagNotFoundException;

	void delete(Tag tag) throws TagNotFoundException;

	void deleteById(Long id) throws TagNotFoundException;

}
