package cn.feiruishu.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    private int id;
    private String name;
    private String key_word;
    private String des;
    private String pic;
    private String atime;
    private String mtime;
    private int status;
    private String topshow;

}
