package com.newha.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.BoardService;
import com.newha.service.UserService;
import com.newha.vo.Board;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("BoardController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BoardController {
	@Autowired
	private BoardService service;

	@Autowired
	private UserService userservice;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "게시글 정보 insert", notes = "게시글 정보 insert 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PostMapping(value = "/boardInsert")
	public ResponseEntity<Map<String, String>> boardInsert(
			@ApiParam(value = "Board", required = true) @RequestBody Board b) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardInsert(b);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	@ApiOperation(value = "게시판 생성", notes = "게시판 생성 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PostMapping(value = "/boardCreate")
	public ResponseEntity<Map<String, String>> boardCreate(
			@ApiParam(value = "String", required = true) @RequestParam String id) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardCreate(id);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	@ApiOperation(value = "게시판 리스트", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@GetMapping(value = "/boardList")
	public ResponseEntity<List<Map<String, String>>> boardList(
			@ApiParam(value = "String", required = true) @RequestParam String id) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int userNo = userservice.userNo(id);
		HttpStatus status = null;
		try {
			List<Integer> l = service.boardListNo(userNo);
			for (int i = 0; i < l.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				Board board = service.selectBoard(l.get(i));
				map.put("boardPostNo", board.getBoardPostNo());
				map.put("title", board.getTitle());
				map.put("date", board.getDate());
				map.put("visit_cnt", board.getVisit_cnt());
				list.add(map);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Map<String, String>>>(list, status);
	}
	
	@ApiOperation(value = "게시판 업데이트", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@PutMapping(value = "/boardUpdate")
	public ResponseEntity<Map<String, String>> boardUpdate(@ApiParam(value = "Board", required = true) @RequestBody Board b){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardUpdate(b);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
		
	}
	@ApiOperation(value = "게시판 삭제", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@DeleteMapping(value = "/boardDelete")
	public ResponseEntity<Map<String, String>> boardDelete(@ApiParam(value = "String", required = true) @RequestParam String boardPostNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardDelete(boardPostNo);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}

	//@PutMapping(value= "/visitCnt")
	//public void visitCnt(@ApiParam(value = "String", required = true) @RequestParam String boardPostNo) {	
	//}
	
}
