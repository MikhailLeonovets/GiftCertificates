package com.itechart.esm.repository.jdbc_template;

import com.itechart.esm.repository.GiftCertificateRepository;
import com.itechart.esm.repository.entity.GiftCertificate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateJdbcRepository implements GiftCertificateRepository {
	@Override
	public GiftCertificate save(GiftCertificate giftCertificate) {
		return null;
	}

	@Override
	public List<GiftCertificate> findAll() {
		return null;
	}

	@Override
	public Optional<GiftCertificate> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public GiftCertificate update(GiftCertificate giftCertificate) {
		return null;
	}

	@Override
	public void delete(GiftCertificate giftCertificate) {

	}

	@Override
	public void deleteById(Long id) {

	}
}
