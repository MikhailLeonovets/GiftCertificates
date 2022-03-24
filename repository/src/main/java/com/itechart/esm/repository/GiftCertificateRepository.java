package com.itechart.esm.repository;

import com.itechart.esm.repository.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateRepository {

	GiftCertificate save(GiftCertificate giftCertificate);

	List<GiftCertificate> findAll();

	Optional<GiftCertificate> findById(Long id);

	List<GiftCertificate> findByPartOfName(String name);

	List<GiftCertificate> findByPartOfDescription(String description);

	boolean update(GiftCertificate giftCertificate);

	boolean delete(GiftCertificate giftCertificate);

	boolean deleteById(Long id);

}
