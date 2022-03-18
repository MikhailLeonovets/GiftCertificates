package com.itechart.esm.service.impl;

import com.itechart.esm.repository.entity.GiftCertificate;
import com.itechart.esm.repository.entity.GiftCertificateTag;
import com.itechart.esm.repository.entity.Tag;
import com.itechart.esm.service.GiftCertificateManagementService;
import com.itechart.esm.service.GiftCertificateService;
import com.itechart.esm.service.GiftCertificateTagService;
import com.itechart.esm.service.TagService;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import com.itechart.esm.service.exception.TagNotFoundException;
import com.itechart.esm.service.model.GiftCertificateAndItsTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GiftCertificateManagementServiceImpl implements GiftCertificateManagementService {
	private final GiftCertificateService giftCertificateService;
	private final TagService tagService;
	private final GiftCertificateTagService giftCertificateTagService;

	@Autowired
	public GiftCertificateManagementServiceImpl(GiftCertificateService giftCertificateService,
	                                            TagService tagService,
	                                            GiftCertificateTagService giftCertificateTagService) {
		this.giftCertificateService = giftCertificateService;
		this.tagService = tagService;
		this.giftCertificateTagService = giftCertificateTagService;
	}

	@Override
	public void createGiftCertificate(GiftCertificateAndItsTags giftCertificateAndItsTags)
			throws DataInputException {
		giftCertificateService.save(giftCertificateAndItsTags.getGiftCertificate());
		for (Tag includedTag : giftCertificateAndItsTags.getTags()) {
			tagService.save(includedTag);
			GiftCertificateTag giftCertificateTag =
					new GiftCertificateTag(giftCertificateAndItsTags.getGiftCertificate(), includedTag);
			giftCertificateTagService.save(giftCertificateTag);
		}
	}

	@Override
	public List<GiftCertificateAndItsTags> findAll()
			throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException {
		List<GiftCertificateAndItsTags> giftCertificatesAndItsTags = new ArrayList<>();
		List<GiftCertificate> giftCertificates = giftCertificateService.findAll();
		if (giftCertificates.isEmpty()) {
			return giftCertificatesAndItsTags;
		}
		for (GiftCertificate giftCertificate : giftCertificates) {
			giftCertificatesAndItsTags.add(createGiftCertificateAndItsTags(giftCertificate));
		}
		return giftCertificatesAndItsTags;
	}

	@Override
	public List<GiftCertificateAndItsTags> findByTag(Tag tag)
			throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException {
		List<GiftCertificateTag> giftCertificatesTags = giftCertificateTagService.findByTag(tag);
		List<GiftCertificate> giftCertificates = giftCertificatesTags.stream()
				.map(GiftCertificateTag::getGiftCertificate).toList();
		List<GiftCertificateAndItsTags> giftCertificatesAndItsTags = new ArrayList<>();
		for (GiftCertificate giftCertificate : giftCertificates) {
			giftCertificatesAndItsTags.add(createGiftCertificateAndItsTags(giftCertificate));
		}
		return giftCertificatesAndItsTags;
	}

	@Override
	public void update(GiftCertificateAndItsTags giftCertificateAndItsTags)
			throws GiftCertificateNotFoundException, DataInputException, TagNotFoundException {
		giftCertificateService.update(giftCertificateAndItsTags.getGiftCertificate());
		Set<Tag> actualTags = giftCertificateAndItsTags.getTags();
		Set<Tag> databaseTags
				= giftCertificateTagService.findByGiftCertificate
						(giftCertificateAndItsTags.getGiftCertificate()).stream()
				.map(GiftCertificateTag::getTag).collect(Collectors.toSet());
		//Set<Tag> tagsToAdd = actualTags.stream()
	}

	@Override
	public void delete(GiftCertificateAndItsTags giftCertificateAndItsTags)
			throws GiftCertificateNotFoundException, DataInputException {
		giftCertificateService.deleteById(giftCertificateAndItsTags.getGiftCertificate().getId());
	}

	private GiftCertificateAndItsTags createGiftCertificateAndItsTags(GiftCertificate giftCertificate)
			throws DataInputException, TagNotFoundException, GiftCertificateNotFoundException {
		Set<Tag> tags =
				giftCertificateTagService.findByGiftCertificate(giftCertificate)
						.stream()
						.map(GiftCertificateTag::getTag)
						.collect(Collectors.toSet());
		return new GiftCertificateAndItsTags(giftCertificate, tags);
	}
}
