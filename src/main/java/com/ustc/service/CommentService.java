package com.ustc.service;

import com.ustc.pojo.Comment;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-28 16:02
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    void saveComment(Comment comment);
}
