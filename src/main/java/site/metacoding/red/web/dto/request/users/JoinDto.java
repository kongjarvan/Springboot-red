package site.metacoding.red.web.dto.request.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JoinDto {
	private String username;
	private String password;
	private String email;
}
