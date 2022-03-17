package com.itechart.esm.repository;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.GiftCertificateTag;
import com.itechart.esm.repository.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateTagRepository {
	GiftCertificateTag save(GiftCertificateTag giftCertificateTag);

	List<GiftCertificateTag> findAll();

	Optional<GiftCertificateTag> findById(Long id);

	List<GiftCertificateTag> findByTag(Tag tag);

	List<GiftCertificateTag> findByGiftCertificate(GiftCertificate giftCertificate);

	boolean delete(GiftCertificateTag giftCertificateTag);

	boolean deleteById(Long id);

}
