package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id));
	}
	
	@GetMapping ("/users")
	public RespDto<?> findAll() {		
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto){
		usersDao.insert(joinDto);
		return new RespDto<>(1, "성공", null);
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		//1. 영속화
		Users UsersPS= usersDao.findById(id);
		
		//2. 변경
		UsersPS.전체수정(updateDto);
		
		//3. 영속화된 오브젝트 update
		usersDao.update(UsersPS);
		
		return new RespDto<>(1, "수정완료", null);
	}
	
	
	@PutMapping("/users/{id}/password")
	public RespDto<?> update(@PathVariable Integer id, String password){
		//1. 영속화
		Users UsersPS= usersDao.findById(id);
		
		//2. 변경
		UsersPS.비밀번호수정(password);
		
		//3. 영속화된 오브젝트 update
		usersDao.update(UsersPS);
		
		return new RespDto<>(1, "수정완료", null);
	}
	
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		usersDao.delete(id);
		return new RespDto<>(1, "삭제완료", null);
	}
	
}
