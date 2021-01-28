package com.newha.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.JwtService;
import com.newha.service.UserService;
import com.newha.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService service;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping(value = "/user") //모든 회원 정보
	public List<User> selectAll() { 
		return service.selectAll();
	}
	
	@GetMapping(value = "/idcheck/{id}") // 아이디체크
	public Map<String, String> selectId(@PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		System.out.println(id);
		int result = service.selectId(id);
		System.out.println(result);
		if(result == 0)
			map.put("message", "success"); // 0이면 없는거 1이면 있는거
		else
			map.put("message", "fail");
		return map;
	}
	
	@GetMapping(value = "/emailauth/{id}") // 이메일 인증
	public Map<String, Integer> emailauth(@PathVariable String id) throws MessagingException {
		int confirm = (int) ((Math.random() * (9999 - 1000)) + 1000);
		
 		Map<String, Integer> map = new HashMap<>();
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("뉴하 이메일 인증입니다.");
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(id));
		message.setText("인증번호: " + confirm);
		message.setSentDate(new Date());
		javaMailSender.send(message);
		map.put("confirm", confirm);
		return map;
	}
	
	@GetMapping(value = "/subscribe/{id}") // 내가 구독한 큐레이터
	public ArrayList<Map<String, String>> subscribe(@PathVariable String id) {
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		int userNo = service.userNo(id);
		System.out.println(userNo);
		List<Integer> l = service.follow(userNo);
		System.out.println(l);
		for (int i = 0; i < l.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			User user = service.selectUser(l.get(i));
			map.put("name", user.getName());
			map.put("thumbnail_path", user.getThumbnail_path());
			list.add(map);
		}
		return list;
	}
	
	
	@PostMapping(value = "/join")
	public Map<String, String> insert(@RequestBody User u /*, @RequestParam List<String> tag*/ ) {
		Map<String, String> map = new HashMap<>();
		int result = service.insert(u);
		int userNo = service.userNo(u.getId());
		/*
		for (int i = 0; i < tag.size(); i++) {
			service.inserttag(userNo, tag.get(i));
		}
		*/
		System.out.println("userNo:"+userNo);
		if(result == 0)
			map.put("message", "회원가입 실패");
		else
			map.put("message", "회원가입 성공");
		System.out.println(map);
		return map;
	}
	
	
	
	@DeleteMapping(value ="/delete/{id}")
	public String delete(@PathVariable String id) {
		service.delete(id);
		return "삭제완료";
	}
	@PutMapping(value ="/update")
	public String update(@RequestBody User u) {
		service.update(u);
		return "수정완료";
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
	//로그인
	@PostMapping(value="/user/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
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
	//사람검색
	@GetMapping(value="/search/people/{keyword}")
	public List<User> searchUser(@PathVariable String keyword){
		return service.searchUser(keyword+"%");
	}
}