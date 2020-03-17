package com.leica.common.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 *
 * @author Leica
 * @date 2020/3/13 14:46
 */
@NoRepositoryBean
public interface BaseMapper<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}
