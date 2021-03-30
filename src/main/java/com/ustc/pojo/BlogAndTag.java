package com.ustc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luoxing
 * @create 2021-03-24 20:13
 * 博客和标签是多对多
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {

    private Long tagId;

    private Long blogId;
}

