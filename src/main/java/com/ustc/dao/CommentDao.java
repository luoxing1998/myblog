package com.ustc.dao;

import com.ustc.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-28 16:02
 */
@Mapper
@Repository
public interface CommentDao {


    //找出没有父评论的一级评论
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId);

    List<Comment> findByParentId (Long parentId);

    Comment findByCommentId(Long commentId);

    void saveComment(Comment comment);
}
