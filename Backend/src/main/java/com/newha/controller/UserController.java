package com.newha.controller;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newha.repository.UserRepository;
import com.newha.vo.Ctest;
import com.newha.vo.User;
import com.sun.xml.bind.v2.model.core.Element;

@RestController
@RequestMapping("/newha")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/test")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	@GetMapping("/url")
	public void getUrl() throws IOException {
		String testurl = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid=366&aid=0000658350";
		
		org.jsoup.nodes.Document doc = Jsoup.connect(testurl).get();
		Elements contents = doc.select("table tbody tr td div div");
		for(org.jsoup.nodes.Element content : contents) {
			Elements tdContents = contents.select("div");
			
			Ctest ctest = new Ctest();
			ctest.setTitle(content.select("h3").text());
			ctest.setContent(content.select("span").text());
		}
	}
}
