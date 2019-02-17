package cn.feiruishu.dao;

import cn.feiruishu.pojo.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {

	@Select("select * from sy_org_role ")
	public List<Demo> findUsers();
	
	@Select("select * from sy_org_role where user_id = #{userId}")
	public Demo findUserById(Long userId);
	
	@Select("select * from sy_org_role "+
			" a " +
			" limit  #{e} " +
			" offset #{s} " )
	public List<Demo> findUserByPage(@Param("s") int start, @Param("e") int end);
	
	@Select("select count(1) from sy_org_role ")
	public int countUserByPage();
}
