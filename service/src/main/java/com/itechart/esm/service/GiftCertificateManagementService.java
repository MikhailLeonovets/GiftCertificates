package com.itechart.esm.service;

import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import com.itechart.esm.service.exception.TagNotFoundException;
import com.itechart.esm.service.model.GiftCertificateAndItsTags;

import java.util.List;

public interface GiftCertificateManagementService {

	void createGiftCertificate(GiftCertificateAndItsTags giftCertificateAndItsTags) throws DataInputException;

	List<GiftCertificateAndItsTags> findAll() throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException;

	List<GiftCertificateAndItsTags> findByTag(Tag tag) throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException;

	void update(GiftCertificateAndItsTags giftCertificateAndItsTags) throws GiftCertificateNotFoundException, DataInputException;

	void delete(GiftCertificateAndItsTags giftCertificateAndItsTags) throws GiftCertificateNotFoundException, DataInputException;

}
