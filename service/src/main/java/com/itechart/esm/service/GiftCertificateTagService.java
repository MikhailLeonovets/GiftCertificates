package com.itechart.esm.service;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.GiftCertificateTag;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import com.itechart.esm.service.exception.GiftCertificateTagNotFoundException;
import com.itechart.esm.service.exception.TagNotFoundException;

import java.util.List;

public interface GiftCertificateTagService {

	void save(GiftCertificateTag giftCertificateTag) throws DataInputException;

	List<GiftCertificateTag> findAll() throws GiftCertificateTagNotFoundException, DataInputException,
			GiftCertificateNotFoundException, TagNotFoundException;

	GiftCertificateTag findById(Long id) throws GiftCertificateTagNotFoundException, DataInputException,
			GiftCertificateNotFoundException, TagNotFoundException;

	List<GiftCertificateTag> findByTag(Tag tag) throws DataInputException, GiftCertificateNotFoundException,
			TagNotFoundException;

	List<GiftCertificateTag> findByGiftCertificate(GiftCertificate giftCertificate) throws DataInputException,
			GiftCertificateNotFoundException, TagNotFoundException;

	GiftCertificateTag findByTagIdAndGiftCertificateId(Long tagId, Long giftCertificateId)
			throws GiftCertificateTagNotFoundException;

	void delete(GiftCertificateTag giftCertificateTag) throws GiftCertificateTagNotFoundException, DataInputException;

	void deleteById(Long id) throws GiftCertificateTagNotFoundException, DataInputException;

}
