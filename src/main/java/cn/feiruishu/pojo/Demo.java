package cn.feiruishu.pojo;

import lombok.Data;

/**
 * 
 * @author ruaho
 *
 */
@Data  //lombok 可以不写get 和set 方法
public class Demo {
	private Long userId ;
	private String userName;
	private String sex;
	private String city;
	private String sign;
	private Long exp;
	private Long scope;
	private String job;
	private Long wealth;
}
