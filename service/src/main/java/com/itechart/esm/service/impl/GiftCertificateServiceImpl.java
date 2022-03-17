package com.itechart.esm.service.impl;

import com.itechart.esm.repository.GiftCertificateRepository;
import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.service.GiftCertificateService;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
	private final GiftCertificateRepository giftCertificateRepository;

	@Autowired
	public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository) {
		this.giftCertificateRepository = giftCertificateRepository;
	}

	@Override
	public void save(GiftCertificate giftCertificate) {
		giftCertificateRepository.save(giftCertificate);
	}

	@Override
	public List<GiftCertificate> findAll() {
		return giftCertificateRepository.findAll();
	}

	@Override
	public GiftCertificate findById(Long id) throws GiftCertificateNotFoundException {
		Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(id);
		if (optionalGiftCertificate.isEmpty()) {
			throw new GiftCertificateNotFoundException();
		}
		return optionalGiftCertificate.get();
	}

	@Override
	public void update(GiftCertificate giftCertificate) throws GiftCertificateNotFoundException {
		boolean isUpdated = giftCertificateRepository.update(giftCertificate);
		if (!isUpdated) {
			throw new GiftCertificateNotFoundException();
		}
	}

	@Override
	public void delete(GiftCertificate giftCertificate) throws GiftCertificateNotFoundException {
		boolean isDeleted = giftCertificateRepository.delete(giftCertificate);
		if (!isDeleted) {
			throw new GiftCertificateNotFoundException();
		}
	}

	@Override
	public void deleteById(Long id) throws GiftCertificateNotFoundException {
		boolean isDeleted = giftCertificateRepository.deleteById(id);
		if (!isDeleted) {
			throw new GiftCertificateNotFoundException();
		}
	}
}
