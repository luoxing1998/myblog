package com.ustc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
    private int num ;//该类型博客的数量

    public void setNum(){
        this.num = this.blogs.size();
    }
}
