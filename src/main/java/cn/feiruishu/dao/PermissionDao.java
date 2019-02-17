package cn.feiruishu.dao;

import cn.feiruishu.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface PermissionDao {

	public List<Permission> findAll();
	public List<Permission> findByAdminUserId(int userId);
}
