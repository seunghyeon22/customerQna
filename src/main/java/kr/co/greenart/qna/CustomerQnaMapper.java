package kr.co.greenart.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerQnaMapper {
	// 글 작성 - 2
	@Insert("insert into customerqna (title, content, username, password, is_secure) values(#{qna.title},#{qna.content},#{qna.username},#{qna.password},#{qna.is_secure}) ")
	void insert(@Param("qna")CustomQna qna);

	// 게시글 목록 - 3
	@Select("select article_id, title, username, is_secure from customerqna where is_deleted = 0")
	@Results(id = "CustomQna", value = {
			@Result(column = "article_id", property = "article_id"),
			@Result(column = "title", property = "title"),
			@Result(column = "username", property = "username"),
			@Result(column = "is_secure", property = "is_secure"),
	})
	List<QnaDTO> selectAll();

	// 3-1
	@Select("select article_id, title, username, is_secure from customerqna where is_secure = 0 AND is_deleted = 0")
	@Results(id = "CustomQnaFalse", value = {
			@Result(column = "article_id", property = "article_id"),
			@Result(column = "title", property = "title"),
			@Result(column = "username", property = "username"),
			@Result(column = "is_secure", property = "is_secure"),
	})
	List<QnaDTO> selectAllBySecure();
	
	// 게시글 bird회 - 4
	@Select("select title, content, username from customerqna where article_id = #{article_id} AND is_secure = 0 AND is_deleted = 0")
	CustomQna selectById(int article_id);
	
	// 게시글 비밀 여부 조회- 5
	@Select("select is_secure from customerqna where article_id = #{article_id} ")
	boolean isSucures(int article_id);

	// count 업데이트 - 6
	@Update("update customerqna set views+=1 where article_id =#{article_id}")
	void updateCount(int article_id);

	// 글 논리 삭제 - 7
	@Update("update customerqna set is_deleted = 1 where article_id = #{article_id} AND password = #{password}")
	void deleteQna(int article_id, String password);
	
}
