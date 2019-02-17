package cn.feiruishu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.feiruishu.pojo.Demo;

@Mapper
public interface DemoDao {

	@Select("select * from t_user ")
	public List<Demo> findUsers();
	
	@Select("select * from t_user where user_id = #{userId}")
	public Demo findUserById(Long userId);
	
	@Select("select * from t_user "+
			" a " +
			" limit  #{e} " +
			" offset #{s} " )
	public List<Demo> findUserByPage(@Param("s") int start, @Param("e") int end);
	
	@Select("select count(1) from t_user ")
	public int countUserByPage();
}
