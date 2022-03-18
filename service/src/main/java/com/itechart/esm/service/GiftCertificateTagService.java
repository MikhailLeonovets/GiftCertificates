package com.itechart.esm.service;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.GiftCertificateTag;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateTagNotFoundException;

import java.util.List;

public interface GiftCertificateTagService {

	void save(GiftCertificateTag giftCertificateTag) throws DataInputException;

	List<GiftCertificateTag> findAll();

	GiftCertificateTag findById(Long id) throws GiftCertificateTagNotFoundException, DataInputException;

	List<GiftCertificateTag> findByTag(Tag tag) throws DataInputException;

	List<GiftCertificateTag> findByGiftCertificate(GiftCertificate giftCertificate) throws DataInputException;

	void delete(GiftCertificateTag giftCertificateTag) throws GiftCertificateTagNotFoundException, DataInputException;

	void deleteById(Long id) throws GiftCertificateTagNotFoundException, DataInputException;

}
