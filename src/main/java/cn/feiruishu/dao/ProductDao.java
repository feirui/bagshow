package cn.feiruishu.dao;

import cn.feiruishu.pojo.Demo;
import cn.feiruishu.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductDao {

	@Insert("insert into wb_product(name,key_word,des,pic,atime,mtime,status,topshow)" +
			" values (" +
			"#{name},#{key_word},#{des},#{pic},#{atime},#{mtime},#{status},#{topshow}" +
			")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addProduct(Product p);

	@Update("update   wb_product " +
			"set name= #{name},key_word = #{key_word}," +
			"des = #{des},pic = #{pic},mtime = #{mtime}," +
			"status = #{status},topshow = #{topshow} where id = #{id}")

	public void modifyProduct(Product p);

	@Select("delete from wb_product where id = #{id} ")
	public void deleteProduct(Integer id);
	
	@Select("select * from wb_product where id = #{id}")
	public Product findProductById(Integer id);
	
	@Select("select * from wb_product "+
			" a " +
			" limit  #{e} " +
			" offset #{s} " )
	public List<Product> findAllProduct(@Param("s") int start, @Param("e") int end);

	@Select("select * from wb_product "+
			" a " +
			" where topshow = #{s}" +
			" limit  #{l} " +
			" offset #{o} " )
	public List<Product> findProduct(@Param("o") int start, @Param("l") int end, @Param("s") String topshow);
	
	@Select("select count(1) from wb_product ")
	public int countProductByPage();

	@Select("select count(1) from wb_product where topshow = #{topshow}")
	public int countTopProductByPage(@Param("topshow") String topshow);

}
