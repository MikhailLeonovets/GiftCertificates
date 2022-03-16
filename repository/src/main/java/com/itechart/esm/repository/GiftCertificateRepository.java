package com.itechart.esm.repository;

import com.itechart.esm.repository.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateRepository {

	GiftCertificate save(GiftCertificate giftCertificate);

	List<GiftCertificate> findAll();

	Optional<GiftCertificate> findById(Long id);

	void update(GiftCertificate giftCertificate);

	void delete(GiftCertificate giftCertificate);

	void deleteById(Long id);

}
