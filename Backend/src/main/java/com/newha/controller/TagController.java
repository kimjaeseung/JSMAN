package com.newha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.TagService;
import com.newha.vo.HashTag;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TagController {
	
	@Autowired
	TagService service;

	@GetMapping(value = "/search/tag/{keyword}")
	List<HashTag> searchTag(@PathVariable String keyword){
		return service.searchTag(keyword + "%");
	}
}
