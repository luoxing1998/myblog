package com.ustc.service.Impl;

import com.ustc.NotFoundException;
import com.ustc.dao.TypeDao;
import com.ustc.pojo.Type;
import com.ustc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luoxing
 * @create 2021-03-24 22:21
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;
    @Transactional
    @Override
    public void saveType(Type type) {
        typeDao.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public List<Type> listBlogType() {
        return typeDao.listBlogType();
    }

    @Transactional
    @Override
    public int updateType(Long id, Type type) {
        Type t = typeDao.getTypeById(id);
        if(t == null){//要修改的type不存在
            throw new NotFoundException("不存在该类型");
        }
        return typeDao.updateType(type);
    }

    @Override
    public void deleteTypeById(Long id) {
        typeDao.deleteTypeById(id);
    }
}
