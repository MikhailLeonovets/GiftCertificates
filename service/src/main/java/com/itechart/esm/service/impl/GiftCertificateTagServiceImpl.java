package com.itechart.esm.service.impl;

import com.itechart.esm.repository.GiftCertificateTagRepository;
import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.GiftCertificateTag;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.GiftCertificateTagService;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateTagNotFoundException;

import java.util.List;
import java.util.Optional;

public class GiftCertificateTagServiceImpl implements GiftCertificateTagService {
	private final GiftCertificateTagRepository giftCertificateTagRepository;

	public GiftCertificateTagServiceImpl(GiftCertificateTagRepository giftCertificateTagRepository) {
		this.giftCertificateTagRepository = giftCertificateTagRepository;
	}

	@Override
	public void save(GiftCertificateTag giftCertificateTag) throws DataInputException {
		if (giftCertificateTag == null) {
			throw new DataInputException();
		}
		giftCertificateTagRepository.save(giftCertificateTag);
	}

	@Override
	public List<GiftCertificateTag> findAll() {
		return giftCertificateTagRepository.findAll();
	}

	@Override
	public GiftCertificateTag findById(Long id) throws GiftCertificateTagNotFoundException, DataInputException {
		if (id == null) {
			throw new DataInputException();
		}
		Optional<GiftCertificateTag> optionalGiftCertificateTag = giftCertificateTagRepository.findById(id);
		if (optionalGiftCertificateTag.isEmpty()) {
			throw new GiftCertificateTagNotFoundException();
		}
		return null;
	}

	@Override
	public List<GiftCertificateTag> findByTag(Tag tag) throws DataInputException {
		if (tag == null) {
			throw new DataInputException();
		}
		return giftCertificateTagRepository.findByTag(tag);
	}

	@Override
	public List<GiftCertificateTag> findByGiftCertificate(GiftCertificate giftCertificate) throws DataInputException {
		if (giftCertificate == null) {
			throw new DataInputException();
		}
		return giftCertificateTagRepository.findByGiftCertificate(giftCertificate);
	}

	@Override
	public void delete(GiftCertificateTag giftCertificateTag)
			throws GiftCertificateTagNotFoundException, DataInputException {
		if (giftCertificateTag == null) {
			throw new DataInputException();
		}
		boolean isDeleted = giftCertificateTagRepository.delete(giftCertificateTag);
		if (!isDeleted) {
			throw new GiftCertificateTagNotFoundException();
		}
	}

	@Override
	public void deleteById(Long id) throws GiftCertificateTagNotFoundException, DataInputException {
		if (id == null) {
			throw new DataInputException();
		}
		boolean isDeleted = giftCertificateTagRepository.deleteById(id);
		if (!isDeleted) {
			throw new GiftCertificateTagNotFoundException();
		}
	}
}
