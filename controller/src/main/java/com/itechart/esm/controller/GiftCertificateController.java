package com.itechart.esm.controller;

import com.itechart.esm.service.GiftCertificateManagementService;
import com.itechart.esm.service.exception.DataInputException;
import com.itechart.esm.service.exception.GiftCertificateNotFoundException;
import com.itechart.esm.service.exception.GiftCertificateTagNotFoundException;
import com.itechart.esm.service.exception.TagNotFoundException;
import com.itechart.esm.service.model.GiftCertificateAndItsTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@PropertySource("classpath:response_msg.properties")
public class GiftCertificateController {
	private static final String URL_MAIN_GIFT_CERT_PAGE = "/gift-certificate";
	private static final String URL_GET_ALL_GIFT_CERT = "";
	private static final String URL_GET_BY_ID_GIFT_CERT = "/{id}";
	private static final String URL_DELETE_GIFT_CERT = "/{id}";
	private static final String URL_CREATE_GIFT_CERT = "";
	private static final String URL_UPDATE_GIFT_CERT = "/{id}";

	@Value("${gift_certificate.deleted}")
	private String giftCertificateDeletedSuccessfully;
	@Value("${gift_certificate.created}")
	private String giftCertificateCreatedSuccessfully;
	@Value("${gift_certificate.updated}")
	private String giftCertificateUpdatedSuccessfully;

	private final GiftCertificateManagementService giftCertificateManagementService;

	@Autowired
	public GiftCertificateController(GiftCertificateManagementService giftCertificateManagementService) {
		this.giftCertificateManagementService = giftCertificateManagementService;
	}

	@GetMapping(URL_MAIN_GIFT_CERT_PAGE + URL_GET_ALL_GIFT_CERT)
	public List<GiftCertificateAndItsTags> getGiftCertificates() throws TagNotFoundException,
			GiftCertificateNotFoundException, DataInputException {
		return giftCertificateManagementService.findAll();
	}

	@GetMapping(URL_MAIN_GIFT_CERT_PAGE + URL_GET_BY_ID_GIFT_CERT)
	public GiftCertificateAndItsTags getGiftCertificate(@PathVariable Long id) throws GiftCertificateNotFoundException,
			TagNotFoundException, DataInputException {
		return giftCertificateManagementService.findById(id);
	}

	@DeleteMapping(URL_MAIN_GIFT_CERT_PAGE + URL_DELETE_GIFT_CERT)
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws GiftCertificateNotFoundException,
			DataInputException {
		giftCertificateManagementService.deleteById(id);
		return ResponseEntity.ok(giftCertificateDeletedSuccessfully);
	}

	@PostMapping(URL_MAIN_GIFT_CERT_PAGE + URL_CREATE_GIFT_CERT)
	public ResponseEntity<?> createGiftCertificate(@RequestBody GiftCertificateAndItsTags giftCertificateAndItsTags)
			throws DataInputException {
		giftCertificateManagementService.createGiftCertificate(giftCertificateAndItsTags);
		return ResponseEntity.ok(giftCertificateCreatedSuccessfully);
	}

	@PutMapping(URL_MAIN_GIFT_CERT_PAGE + URL_UPDATE_GIFT_CERT)
	public ResponseEntity<?> updateGiftCertificate(@RequestBody GiftCertificateAndItsTags giftCertificateAndItsTags,
	                                               @PathVariable String id)
			throws GiftCertificateNotFoundException, TagNotFoundException, GiftCertificateTagNotFoundException,
			DataInputException {
		giftCertificateManagementService.update(giftCertificateAndItsTags);
		return ResponseEntity.ok(giftCertificateUpdatedSuccessfully);
	}
}
