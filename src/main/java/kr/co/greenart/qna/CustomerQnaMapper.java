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
	// 글 작성 
	@Insert("insert into customerqna (title, content, username, password, is_secure) values(#{qna.title},#{qna.content},#{qna.username},#{qna.password},#{qna.is_secure}) ")
	void save(@Param("qna")CustomQna qna);

	// 게시글 목록(id, title, username, is_secure)
	@Select("select article_id, title, username, is_secure from customerqna where is_deleted = 0")
	@Results(id = "CustomQna", value = {
			@Result(column = "article_id", property = "article_id"),
			@Result(column = "title", property = "title"),
			@Result(column = "username", property = "username"),
			@Result(column = "is_secure", property = "is_secure"),
	})
	List<QnaDTO> findAll();

	// 게시글 조회 시, is_secure 값이 false인 행만 조회
	@Select("select article_id, title, username, is_secure from customerqna where is_secure = 0 AND is_deleted = 0")
	@Results(id = "CustomQnaFalse", value = {
			@Result(column = "article_id", property = "article_id"),
			@Result(column = "title", property = "title"),
			@Result(column = "username", property = "username"),
			@Result(column = "is_secure", property = "is_secure"),
	})
	List<QnaDTO> findBySecureIsFalse();
	
	// 게시글 조회(id로 검색, title, content, username)
	@Select("select title, content, username from customerqna where article_id = #{article_id} AND is_secure = 0 AND is_deleted = 0")
	CustomQna findById(int article_id);
	
	// 게시글의 비밀 여부 조회 (is_secure)
	@Select("select is_secure from customerqna where article_id = #{article_id} ")
	boolean findBySecureById(int article_id);

	// views count 수정(1 증가)
	@Update("update customerqna set views+=1 where article_id =#{article_id}")
	void updateCount(int article_id);

	// 글 논리 삭제(pk 및 password 일치) : is_deleted => 1로 수정
	@Update("update customerqna set is_deleted = 1 where article_id = #{article_id} AND password = #{password}")
	void deleteQna(int article_id, String password);
	
}
