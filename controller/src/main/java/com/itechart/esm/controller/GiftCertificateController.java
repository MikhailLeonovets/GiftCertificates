package com.itechart.esm.controller;

import com.itechart.esm.repository.entity.GiftCertificate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gift-certificate")
public class GiftCertificateController {

	@GetMapping
	public List<GiftCertificate> getGiftCertificates() {

	}

}
