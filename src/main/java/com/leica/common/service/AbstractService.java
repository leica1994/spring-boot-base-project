package com.leica.common.service;

import com.leica.common.entity.exception.ServiceException;
import com.leica.common.mapper.BaseMapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于通用MyBatis {@link BaseMapper}插件的Service接口的实现
 *
 * @author Leica
 * @date 2020/3/13 15:39
 */
@SuppressWarnings("unchecked")
public abstract class AbstractService<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    public AbstractService() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            this.modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }

    }

    @Override
    public T save(T model) {
        return baseMapper.saveAndFlush(model);
    }

    @Override
    public void save(List<T> models) {
        baseMapper.saveAll(models);
    }

    @Override
    public void deleteById(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(String ids) {
        StringUtils.commaDelimitedListToSet(ids)
                .stream()
                .map(Integer::parseInt)
                .forEach(baseMapper::deleteById);
    }

    @Override
    public void update(T model) {
        baseMapper.save(model);
    }

    @Override
    public T findById(Integer id) {
        return baseMapper.findById(id).orElse(null);
    }

    @Override
    public List<T> findByIds(String ids) {
        List<Integer> idsList = StringUtils.commaDelimitedListToSet(ids)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return baseMapper.findAllById(idsList);
    }

    @Override
    public List<T> findAll() {
        return baseMapper.findAll();
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.getConstructor().newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return baseMapper.findOne(Example.of(model)).orElse(null);
        } catch (ReflectiveOperationException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<T> findByExample(Example<T> example) {
        return baseMapper.findAll(example);
    }

    @Override
    public Page<T> findByPage(int pageNum, int pageSize) {
        return baseMapper.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Page<T> findByPageAndExample(int pageNum, int pageSize, Example<T> example) {
        return baseMapper.findAll(example, PageRequest.of(pageNum, pageSize));
    }
}
