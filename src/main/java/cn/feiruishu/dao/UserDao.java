package cn.feiruishu.dao;

import cn.feiruishu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

	@Select("select * from sy_org_user ")
	public List<User> findUsers();
	
	@Select("select * from sy_org_user where user_id = #{userId}")
	public User findUserById(Long userId);
	
	@Select("select * from sy_org_user "+
			" a " +
			" limit  #{e} " +
			" offset #{s} " )
	public List<User> findUserByPage(@Param("s") int start, @Param("e") int end);
	
	@Select("select count(1) from sy_org_user ")
	public int countUserByPage();

	@Select("select * from sy_org_user where username = #{username} ")
	public User findUserByUsername(@Param("username") String username);
}
