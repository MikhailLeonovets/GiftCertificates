package com.itechart.esm.repository.jdbc_template;

import com.itechart.esm.repository.GiftCertificateRepository;
import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.mapper.GiftCertificateMapper;
import com.itechart.esm.utilities.converter.PostgresIntervalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class GiftCertificateSpringJdbcRepository implements GiftCertificateRepository {
	private static final String INSERT_GIFT_CERTIFICATE_QUERY
			= "INSERT INTO gift_certificate (name, description, price, date_of_creation, duration) " +
			"VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_GIFT_CERTIFICATE_QUERY
			= "SELECT * FROM gift_certificate";
	private static final String SELECT_GIFT_CERTIFICATE_BY_ID_QUERY
			= "SELECT * FROM gift_certificate WHERE id = ?";
	private static final String UPDATE_GIFT_CERTIFICATE_QUERY
			= "UPDATE gift_certificate SET " +
			"name = ?, " +
			"description = ?, " +
			"price = ?, " +
			"date_of_modification = ?, " +
			"duration = ? " +
			"WHERE id =?";
	private static final String DELETE_GIFT_CERTIFICATE_BY_ID_QUERY
			= "DELETE FROM gift_certificate WHERE id = ?";

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GiftCertificateSpringJdbcRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		jdbcTemplate.setDataSource(dataSource);
	}

	@Override
	public GiftCertificate save(GiftCertificate giftCertificate) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_GIFT_CERTIFICATE_QUERY,
					new String[]{"id"});
			preparedStatement.setString(1, giftCertificate.getName());
			preparedStatement.setString(2, giftCertificate.getDescription());
			preparedStatement.setBigDecimal(3, giftCertificate.getPrice());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(giftCertificate.getDateOfCreation()));
			preparedStatement.setObject(5,
					PostgresIntervalConverter.convertTimePeriodToPGInterval(giftCertificate.getExpirationPeriod()),
					Types.OTHER);
			return preparedStatement;
		}, keyHolder);
		giftCertificate.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		return giftCertificate;
	}

	@Override
	public List<GiftCertificate> findAll() {
		return jdbcTemplate.query(SELECT_ALL_GIFT_CERTIFICATE_QUERY, new GiftCertificateMapper());
	}

	@Override
	public Optional<GiftCertificate> findById(Long id) {
		return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_GIFT_CERTIFICATE_BY_ID_QUERY,
				new GiftCertificateMapper(), id));
	}

	@Override
	public boolean update(GiftCertificate giftCertificate) {
		return jdbcTemplate.update(UPDATE_GIFT_CERTIFICATE_QUERY, giftCertificate.getName(),
				giftCertificate.getDescription(), giftCertificate.getPrice(),
				Timestamp.valueOf(giftCertificate.getDateOfModification()),
				PostgresIntervalConverter.convertTimePeriodToPGInterval(giftCertificate.getExpirationPeriod()),
				giftCertificate.getId()) > 0;
	}

	@Override
	public boolean delete(GiftCertificate giftCertificate) {
		return jdbcTemplate.update(DELETE_GIFT_CERTIFICATE_BY_ID_QUERY, giftCertificate.getId()) > 0;
	}

	@Override
	public boolean deleteById(Long id) {
		return jdbcTemplate.update(DELETE_GIFT_CERTIFICATE_BY_ID_QUERY, id) > 0;
	}
}