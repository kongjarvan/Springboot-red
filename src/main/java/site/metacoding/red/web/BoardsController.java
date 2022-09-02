package site.metacoding.red.web;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor  // final 붙은 놈들만 생성자 만들어줌
@RestController
public class BoardsController {
	
	
	private final BoardsDao boardsDao;
	
	
	@PostMapping("/boards")
	public RespDto<?> insert(WriteDto writeDto){
		boardsDao.insert(writeDto);
		return new RespDto<>(1, "글쓰기 성공", null);
	}
	
	
	@GetMapping("/boards")
	public RespDto<?> findAll(){
		return new RespDto<>(1, "조회 성공", boardsDao.findAll());
	}
	
	
	@GetMapping("/boards/{id}")
	public RespDto<?> findByIdtoDetail(@PathVariable Integer id){
		return new RespDto<>(1, "조회 성공", boardsDao.findByIdtoDetail(id));
	}
	
	@PutMapping("/boards/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		
		//1. 영속화
		Boards boards = boardsDao.findById(id);
		
		
		//2. 변경
		boards.전체수정(updateDto);
		
		
		//3. update 실행
		boardsDao.update(boards);	

		return new RespDto<>(1, "수정완료", null);
	}

	
	@PutMapping("/boards/{id}/content")
	public RespDto<?> updateContent(@PathVariable Integer id, String content){
		
		//1. 영속화
		Boards boards = boardsDao.findById(id);
		
		
		//2. 변경
		boards.내용수정(content);
		
		
		//3. update 실행
		boardsDao.update(boards);	

		return new RespDto<>(1, "수정완료", null);
	}
	
	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1, "삭제완료", null);
	}
	
	
}
