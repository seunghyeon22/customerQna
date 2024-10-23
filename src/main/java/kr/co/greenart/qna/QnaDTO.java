package kr.co.greenart.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class QnaDTO {
	private int article_id;
	private String title;
	private String username;
	private boolean is_secure;
}
