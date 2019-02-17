package cn.feiruishu.dao;

import cn.feiruishu.pojo.FileForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileDao {

	@Insert("insert into wb_File(name,fileurl,object_id,time,type)" +
			" values (" +
			"#{name},#{fileurl},#{object_id},#{time},#{type}" +
			")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addFile(FileForm p);

	@Update("update   wb_File " +
			"set name= #{name},fileurl = #{fileurl}," +
			"object_id = #{object_id},time = #{time},type = #{type} " +
			" where id = #{id}")

	public void modifyFile(FileForm p);

	@Select("delete from wb_File where id = #{id} ")
	public void deleteFile(Integer id);

	@Select("select * from wb_File where id = #{id}")
	public FileForm findFileById(Integer id);

	@Select("select * from wb_File "+
			" a " +
			" limit  #{e} " +
			" offset #{s} " )
	public List<FileForm> findAllFile(@Param("s") int start, @Param("e") int end);

	/*@Select("select * from wb_File "+
			" a " +
			" where object_id = #{s}" +
			" limit  #{l} " +
			" offset #{o} " )*/
	//public List<FileForm> findFilesByProID(@Param("o") int start, @Param("l") int end, @Param("s") int is_topshow);

	@Select("select * from wb_File "+
			" a " +
			" where object_id = #{s}" )
	public List<FileForm> findFilesByProID( @Param("s") int object_id);

	@Select("select count(1) from wb_File ")
	public int countFileByPage();

}
