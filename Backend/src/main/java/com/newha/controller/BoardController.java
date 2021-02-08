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
import com.newha.vo.BoardComment;

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
			@ApiParam(value = "Board", required = true) @RequestBody Board b,
			@ApiParam(value = "String", required = true) @RequestParam String id
			) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;

		try {
			String userNo = Integer.toString(userservice.userNo(id));
			b.setUserNo(userNo);
			service.boardInsert(b);
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
				String temp = Integer.toString(l.get(i));
				Map<String, String> map = new HashMap<String, String>();
				Board board = service.selectBoard(temp);
				String boardCommentCount = service.boardCommentCount(temp);
				map.put("boardPostNo", board.getBoardPostNo());
				map.put("title", board.getTitle());
				map.put("date", board.getDate());
				map.put("visit_cnt", board.getVisit_cnt());
				map.put("userNo", board.getUserNo());
				map.put("content", board.getContent());
				map.put("is_notice", board.getIs_notice());
				
				map.put("boardCommentCount", boardCommentCount); // 댓글 갯수
				
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
	public ResponseEntity<Map<String, String>> boardUpdate(
			@ApiParam(value = "Board", required = true) @RequestBody Board b) {
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
	public ResponseEntity<Map<String, String>> boardDelete(
			@ApiParam(value = "String", required = true) @RequestParam String boardPostNo) {
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


	@ApiOperation(value = "게시글", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@GetMapping(value = "/boardCommentList")
	public ResponseEntity<List<Map<String, String>>> boardCommentList(
			@ApiParam(value = "int", required = true) @RequestParam String boardPostNo) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		HttpStatus status = null;
		
		try {
			List<Integer> l = service.boardCommentList(boardPostNo);

			for (int i = 0; i < l.size(); i++) {
				String commentNo = Integer.toString(l.get(i));
				BoardComment bc = service.boardComment(commentNo);
				Map<String, String> temp = new HashMap<String, String>();
				temp.put("BoardPostNo", bc.getBoardPostNo());
				temp.put("CommentNo", bc.getCommentNo());
				temp.put("Content", bc.getContent());
				temp.put("Date", bc.getDate());
				temp.put("UserNo", bc.getUserNo());
				list.add(temp);
			}
			
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		service.visitCnt(boardPostNo);
		return new ResponseEntity<List<Map<String, String>>>(list, status);
	}
	
	@ApiOperation(value = "게시글 댓글 insert", notes = "게시글 댓글 insert 결과'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PostMapping(value = "/boardCommentInsert")
	public ResponseEntity<List<Map<String, String>>> boardCommentInsert(
			@ApiParam(value = "String", required = true) @RequestParam String boardPostNo,
			@ApiParam(value = "String", required = true) @RequestParam String id,
			@ApiParam(value = "String", required = true) @RequestParam String content) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		HttpStatus status = null;
		try {
			service.boardCommentInsert(boardPostNo, id, content);
			List<Integer> l = service.boardCommentList(boardPostNo);
			for (int i = 0; i < l.size(); i++) {
				String commentNo = Integer.toString(l.get(i));
				BoardComment bc = service.boardComment(commentNo);
				Map<String, String> temp = new HashMap<String, String>();
				temp.put("BoardPostNo", bc.getBoardPostNo());
				temp.put("CommentNo", bc.getCommentNo());
				temp.put("Content", bc.getContent());
				temp.put("Date", bc.getDate());
				temp.put("UserNo", bc.getUserNo());
				list.add(temp);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Map<String, String>>>(list, status);
	}
	
	@ApiOperation(value = "댓글 삭제", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@DeleteMapping(value = "/boardCommentDelete")
	public ResponseEntity<Map<String, String>> boardCommentDelete(
			@ApiParam(value = "String", required = true) @RequestParam String commentNo) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardCommentDelete(commentNo);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "댓글 수정", notes = "성공/실패 여부에 따라 http 상태코드 출력", response = Map.class)
	@PutMapping(value = "/boardCommentUpdate")
	public ResponseEntity<Map<String, String>> boardCommentUpdate(
			@ApiParam(value = "Board", required = true) @RequestBody BoardComment bc) {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			service.boardCommentUpdate(bc);
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	
	
}
