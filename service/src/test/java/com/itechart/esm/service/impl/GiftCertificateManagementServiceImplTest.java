package com.itechart.esm.service.impl;

import com.itechart.esm.service.GiftCertificateService;
import com.itechart.esm.service.GiftCertificateTagService;
import com.itechart.esm.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GiftCertificateManagementServiceImplTest {
	private GiftCertificateManagementServiceImpl giftCertificateManagementService;

	@Mock
	private GiftCertificateService giftCertificateService;
	@Mock
	private TagService tagService;
	@Mock
	private GiftCertificateTagService giftCertificateTagService;

	@BeforeEach
	void setUp() {
		giftCertificateManagementService = new GiftCertificateManagementServiceImpl(giftCertificateService,
				tagService, giftCertificateTagService);
	}

	@Test
	void createGiftCertificate() {

	}

	@Test
	void findAll() {
	}

	@Test
	void findAllSortByDateOfCreation() {
	}

	@Test
	void findAllSortByName() {
	}

	@Test
	void findById() {
	}

	@Test
	void findByTag() {
	}

	@Test
	void testFindByTag() {
	}

	@Test
	void testFindByTag1() {
	}

	@Test
	void findByPartOfName() {
	}

	@Test
	void findByPartOfDescription() {
	}

	@Test
	void update() {
	}

	@Test
	void delete() {
	}

	@Test
	void deleteById() {
	}
}