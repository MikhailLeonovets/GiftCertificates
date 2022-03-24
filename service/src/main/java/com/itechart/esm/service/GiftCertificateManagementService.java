package com.itechart.esm.service;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import com.itechart.esm.service.exception.GiftCertificateTagNotFoundException;
import com.itechart.esm.service.exception.TagNotFoundException;
import com.itechart.esm.service.model.GiftCertificateAndItsTags;

import java.util.List;

public interface GiftCertificateManagementService {

	void createGiftCertificate(GiftCertificateAndItsTags giftCertificateAndItsTags) throws DataInputException;

	List<GiftCertificateAndItsTags> findAll() throws DataInputException, TagNotFoundException,
			GiftCertificateNotFoundException;

	GiftCertificateAndItsTags findById(Long id) throws GiftCertificateNotFoundException, DataInputException,
			TagNotFoundException;

	List<GiftCertificateAndItsTags> findByTag(Tag tag) throws DataInputException, TagNotFoundException,
			GiftCertificateNotFoundException;

	List<GiftCertificateAndItsTags> findByTag(String tagName) throws DataInputException, TagNotFoundException,
			GiftCertificateNotFoundException;

	List<GiftCertificateAndItsTags> findByTag(Long tagId) throws DataInputException, TagNotFoundException,
			GiftCertificateNotFoundException;

	List<GiftCertificateAndItsTags> findByPartOfName(String name) throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException;

	List<GiftCertificateAndItsTags> findByPartOfDescription(String description) throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException;

	void update(GiftCertificateAndItsTags giftCertificateAndItsTags) throws GiftCertificateNotFoundException,
			DataInputException, TagNotFoundException, GiftCertificateTagNotFoundException;

	void delete(GiftCertificateAndItsTags giftCertificateAndItsTags) throws GiftCertificateNotFoundException,
			DataInputException;

	void deleteById(Long id) throws GiftCertificateNotFoundException, DataInputException;

}
