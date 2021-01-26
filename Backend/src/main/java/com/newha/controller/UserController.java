package com.newha.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newha.repository.UserRepository;
import com.newha.service.JwtService;
import com.newha.service.UserService;
import com.newha.vo.Ctest;
import com.newha.vo.User;

@RestController
@RequestMapping("/newha")
@CrossOrigin
public class UserController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService service;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
//	@Autowired
//	private UserRepository userRepository;
//
//	@GetMapping("/test")
//	public List<User> getAllUser() {
//		return userRepository.findAll();
//	}
//	@GetMapping("/url") 
//	public void getUrl() throws IOException {
//		String testurl = "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid=366&aid=0000658350";
//		
//		org.jsoup.nodes.Document doc = Jsoup.connect(testurl).get();
//		Elements contents = doc.select("table tbody tr td div div");
//		for(org.jsoup.nodes.Element content : contents) {
//			Elements tdContents = contents.select("div");
//			
//			Ctest ctest = new Ctest();
//			ctest.setTitle(content.select("h3").text());
//			ctest.setContent(content.select("span").text());
//		}
//	}
	
	//로그인
	@PostMapping(value="/user/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user)
	{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(user);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//토근 유효여부 검사
	@GetMapping(value="/user/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable String id,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(id);
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.userInfo(id);
				System.out.println(user.getId());
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
}
