package cn.feiruishu.pojo;

import lombok.Data;

@Data
public class Permission {
    private int id;
    private String name    ;
    private String description   ;
    private String url  ;
    private int pid  ;
}
