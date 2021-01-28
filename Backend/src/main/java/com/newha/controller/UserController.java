package com.newha.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService service;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "유저 리스트 조회", notes = "유저 리스트를 리턴", response = List.class)
	@GetMapping(value = "/user")
	public List<User> selectAll() {
		return service.selectAll();
	}
	
	@ApiOperation(value = "아이디 중복검사", notes = "아이디 중복검사결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/idcheck/{id}") // 아이디체크
	public Map<String, String> selectid( @ApiParam(value = "id", required = true)@PathVariable String id) {
		Map<String, String> map = new HashMap<>();
		System.out.println(id);
		int result = service.selectid(id);
		System.out.println(result);
		if(result == 0)
			map.put("message", "success"); // 0이면 없는거 1이면 있는거
		else
			map.put("message", "fail");
		return map;
	}
	
	@ApiOperation(value = "회원가입", notes = "회원가입 성공 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PostMapping(value = "/join")
	public Map<String, String> insert(@ApiParam(value = "User", required = true)@RequestBody User u /*,  @ApiParam(value = "tag List", required = true)@RequestParam List<String> tag*/ ) {
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
	
	
	@ApiOperation(value = "회원 탈퇴", notes = "탈퇴 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@DeleteMapping(value ="/delete/{id}")
	public String delete(@ApiParam(value = "id", required = true)@PathVariable String id) {
		service.delete(id);
		return "삭제완료";
	}
	
	@ApiOperation(value = "회원 탈퇴", notes = "수정 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PutMapping(value ="/update")
	public String update(@ApiParam(value = "User", required = true)@RequestBody User u) {
		service.update(u);
		return "수정완료";
	}
	
	//토근 유효여부 검사
	@ApiOperation(value = "로그인", notes = "'success' 또는 'fail', httpstatus, userInfo 리턴", response = Map.class)
	@GetMapping(value="/user/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@ApiParam(value = "id", required = true)@PathVariable String id,
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
	@ApiOperation(value = "토큰 유효성 검사", notes = "'success' 또는 'fail', token 리턴", response = Map.class)
	@PostMapping(value="/user/login")
	public ResponseEntity<Map<String, Object>> login(@ApiParam(value = "id", required = true)@RequestBody User user){
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
	@ApiOperation(value = "유저 검색", notes = "유저 List 리턴", response = List.class)
	@GetMapping(value="/search/people/{keyword}")
	public List<User> searchUser(@ApiParam(value = "keyword", required = true)@PathVariable String keyword){
		return service.searchUser(keyword+"%");
	}
}