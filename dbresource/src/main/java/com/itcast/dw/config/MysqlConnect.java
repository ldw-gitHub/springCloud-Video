package com.itcast.dw.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.itcast.dw.model.MysqlVo;

@Configuration
@MapperScan("com.itcast.dw.dao") // 扫描mapper接口包
public class MysqlConnect implements EnvironmentAware {

	@Resource
	private Environment environment;
	private MysqlVo MysqlVo;

	// EnvironmentAware接口的默认实现方法，默认扫描spring.datasource.属性
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		this.MysqlVo = Binder.get(environment)
				.bind("spring.datasource", MysqlVo.class)
				.orElse(null);
		System.out.println(MysqlVo.getUrl() + "=================");
	}

	// 获得SqlSessionFactory对象
	@Bean
	public SqlSessionFactory SqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	// 获得DataSource对象

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(MysqlVo.getDriverclassname());
		druidDataSource.setUrl(MysqlVo.getUrl());
		druidDataSource.setUsername(MysqlVo.getUsername());
		druidDataSource.setPassword(MysqlVo.getPassword());
		return druidDataSource;
	}
	

}
