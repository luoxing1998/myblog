package com.ustc.service.Impl;

import com.ustc.dao.TagDao;
import com.ustc.pojo.Tag;
import com.ustc.pojo.Type;
import com.ustc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-25 13:46
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;
    @Transactional
    @Override
    public void saveTag(Tag tag) {
        tagDao.saveTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public List<Tag> listBlogTag() {
        return tagDao.listBlogTag();
    }

    @Override
    public List<Tag> listTagByString(String ids) {
        List<Tag> list = new ArrayList<>();
        List<Long> tagIds = convertToList(ids);
        for (Long tagId : tagIds) {
            list.add(tagDao.getTagById(tagId));
        }
        return list;
    }

    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public int updateTag(Long id, Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public void deleteTagById(Long id) {
        tagDao.deleteTagById(id);
    }
}
