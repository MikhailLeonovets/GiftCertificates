package com.itechart.esm.repository.jdbc_template;

import com.itechart.esm.repository.TagRepository;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.repository.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TagSpringJdbcRepository implements TagRepository {
	private static final String SELECT_ALL_TAG_QUERY
			= "SELECT * FROM tag";
	private static final String SELECT_TAG_BY_ID
			= "";
	private static final String INSERT_TAG_QUERY
			= "INSERT INTO tag (name) VALUES (?)";
	private static final String DELETE_TAG_BY_ID
			= "DELETE FROM tag WHERE id = ?";

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TagSpringJdbcRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.setDataSource(dataSource);
	}


	@Override
	public Tag save(Tag tag) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_TAG_QUERY, new String[]{"id"});
			preparedStatement.setString(1, tag.getName());
			return preparedStatement;
		}, keyHolder);
		tag.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return tag;
	}

	@Override
	public List<Tag> findAll() {
		return jdbcTemplate.query(SELECT_ALL_TAG_QUERY, new TagMapper());
	}

	@Override
	public Optional<Tag> findById(Long id) {
		return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_TAG_BY_ID, new TagMapper(), id));
	}

	@Override
	public void delete(Tag tag) {
		jdbcTemplate.update(DELETE_TAG_BY_ID, tag.getId());
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(DELETE_TAG_BY_ID, id);
	}
}
