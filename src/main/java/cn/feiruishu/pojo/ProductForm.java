package cn.feiruishu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ProductForm extends Product{
    private List<FileForm> fileList;
    private String mainPic;
}
