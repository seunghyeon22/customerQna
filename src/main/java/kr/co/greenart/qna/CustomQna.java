package kr.co.greenart.qna;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomQna {
	private Integer article_id;
	private String title;
	private String content;
	private String username;
	private String password;
	private Integer views;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Boolean is_secure;
	private Boolean is_deleted;
}
