package cn.feiruishu.pojo;

import lombok.Data;

@Data
public class User {
    private int userid;
    private String User_uuid;
    private String username;
    private String atime ;
    private String mtime;
    private int status ;
    private String password;    //用户密码
    private String email;       //用户邮箱
    private String telephone;   //电话号码
    private String role;        //用户角色
    private String image;       //用户头像
    private String last_ip;     //上次登录IP
    private String last_time;   //上次登录时间


}
