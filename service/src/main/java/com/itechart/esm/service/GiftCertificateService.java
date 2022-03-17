package com.itechart.esm.service;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;

import java.util.List;

public interface GiftCertificateService {

	void save(GiftCertificate giftCertificate);

	List<GiftCertificate> findAll();

	GiftCertificate findById(Long id) throws GiftCertificateNotFoundException;

	void update(GiftCertificate giftCertificate) throws GiftCertificateNotFoundException;

	void delete(GiftCertificate giftCertificate) throws GiftCertificateNotFoundException;

	void deleteById(Long id) throws GiftCertificateNotFoundException;

}
