package com.ustc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 20:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;
    private int num;

    private List<Blog> blogs = new ArrayList<>();

    public void setNum(){
        this.num=this.blogs.size();
    }
}
