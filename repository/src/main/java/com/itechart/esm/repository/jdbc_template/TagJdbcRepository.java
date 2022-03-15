package com.itechart.esm.repository.jdbc_template;

import com.itechart.esm.repository.TagRepository;
import com.itechart.esm.repository.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagJdbcRepository implements TagRepository {
	@Override
	public Tag save(Tag tag) {
		return null;
	}

	@Override
	public List<Tag> findAll() {
		return null;
	}

	@Override
	public Optional<Tag> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public void delete(Tag tag) {

	}

	@Override
	public void deleteById(Long id) {

	}
}
