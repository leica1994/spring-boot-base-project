package com.leica.common.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @author Leica
 * @date 2020/3/13 15:03
 */
public interface BaseService<T> {
    /**
     * 持久化
     *
     * @param model T
     * @return T 保存后的对象
     */
    T save(T model);

    /**
     * 批量持久化
     *
     * @param models List<T>
     */
    void save(List<T> models);

    /**
     * 根据主键删除
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 批量刪除
     *
     * @param ids ids -> “1,2,3,4”
     */
    void deleteByIds(String ids);

    /**
     * 更新
     *
     * @param model T
     */
    void update(T model);

    /**
     * 通过ID查找
     *
     * @param id 主键id
     * @return T
     */
    T findById(Integer id);

    /**
     * 通过多个ID查找
     *
     * @param ids ids -> “1,2,3,4”
     * @return List<T>
     */
    List<T> findByIds(String ids);

    /**
     * 获取所有
     *
     * @return List<T>
     */
    List<T> findAll();

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     *
     * @param fieldName 字段名
     * @param value     字段值
     * @return T
     * @throws TooManyResultsException {@link TooManyResultsException}异常
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 根据条件查找
     *
     * @param example 条件 {@link Example}
     * @return List<T>
     */
    List<T> findByExample(Example<T> example);

    /**
     * 分页查询所有数据
     *
     * @param pageNum  当前页
     * @param pageSize 每页限制条数
     * @return {@link Page}
     */
    Page<T> findByPage(int pageNum, int pageSize);

    /**
     * 条件并分页查询数据
     *
     * @param pageNum  当前页
     * @param pageSize 每页限制条数
     * @param example  条件 {@link Example}
     * @return {@link Page}
     */
    Page<T> findByPageAndExample(int pageNum, int pageSize, Example<T> example);
}
