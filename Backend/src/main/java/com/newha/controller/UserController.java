package com.newha.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.UserService;
import com.newha.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	/*
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @GetMapping("/test") public List<User> getAllUser() { return
	 * userRepository.findAll(); }
	 * 
	 * @GetMapping("/url") public void getUrl() throws IOException { String testurl
	 * =
	 * "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid=366&aid=0000658350";
	 * 
	 * org.jsoup.nodes.Document doc = Jsoup.connect(testurl).get(); Elements
	 * contents = doc.select("table tbody tr td div div");
	 * for(org.jsoup.nodes.Element content : contents) { Elements tdContents =
	 * contents.select("div");
	 * 
	 * Ctest ctest = new Ctest(); ctest.setTitle(content.select("h3").text());
	 * ctest.setContent(content.select("span").text()); } }
	 */
	@Autowired
	UserService service;
	
	@GetMapping(value = "/user")
	public List<User> selectAll() {
		return service.selectAll();
	}

	@PostMapping(value = "/sign")
	public String insert(@RequestBody User u) {
		service.insert(u);
		System.out.println(u.getId()+"/"+u.getName());
		return "insert완료";
	}
	
	@DeleteMapping(value ="/delete/{id}")
	public String delete(@PathVariable String id) {
		service.delete(id);
		System.out.println(id);
		return "삭제완료";
	}
	@PutMapping(value ="/update")
	public String update(@RequestBody User u) {
		service.update(u);
		
		return "수정완료";
	}
	
	
}
