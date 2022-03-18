package com.itechart.esm.service;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.model.GiftCertificateAndItsTags;

import java.util.List;
import java.util.Set;

public interface GiftCertificateManagementService {

	void createGiftCertificate(GiftCertificate giftCertificate, Set<Tag> includedTags);

	List<GiftCertificateAndItsTags> findAll();

	List<GiftCertificateAndItsTags> findByTag(Tag tag);

	void update(GiftCertificateAndItsTags giftCertificateAndItsTags);

	void delete(GiftCertificateAndItsTags giftCertificateAndItsTags);

}
