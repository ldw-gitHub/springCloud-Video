package com.itcast.dw.jwt;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itcast.dw.config.ProjectConfig;
import com.itcast.dw.config.RedisUtils;
import com.itcast.dw.service.UserService;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private Logger logger = Logger.getLogger(WebSecurityConfig.class);

	@Autowired
	RedisUtils redisUtils;
	@Autowired
	UserService userService;
	@Autowired
	ProjectConfig projectConfig;
	
    /**
     * spring-security加密
     * @return BCryptPasswordEncoder
     * @author sky_luan
     */
    @Bean  
    public BCryptPasswordEncoder bCryptPasswordEncoder() {  
        return new BCryptPasswordEncoder();  
    }
    
	/**
	 * 核心用户业务处理
	 */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * 无权访问处理
	 */
	@Autowired
	private RestAccessDeniedHandler restAccessDeniedHandler;
	
	/**
	 * 登出处理
	 */
	@Autowired
	private MyLogoutHandler myLogoutHandler;


	/**
	 * 授权核心管理
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManager()
	 */
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/**
	 * 核心配置
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 进入核心配置 ");
		// 跨域控制
		http.cors().and().csrf().disable()
		       //请求授权
		       .authorizeRequests()
		       // 静态资源控制,不进行拦截,permitAll容许用户访问
		       .antMatchers(HttpMethod.OPTIONS).permitAll()
		       .antMatchers("/login").permitAll()
		       .antMatchers("/logout").permitAll()
		       .antMatchers(
		    		   HttpMethod.GET,
		    		   "/v2/api-docs",
		    		   "/swagger-resources/**",
		    		   "/swagger-ui.html**",
		    		   "/druid/**",
		    		   "/webjars/**",
		    		   "favicon.ico"
		    		   ).permitAll()
		       .antMatchers(
		    		   HttpMethod.POST,
		    		   "/zuul/video/uploadFile",
		    		   "/video/findVideosByType",
		    		   "/video/findRelateVideos",
		    		   "/video/findVideosById",
		    		   "/video/getVideoCommentsByid",
		    		   "/video/indexFindVideosByType"
		    		   ).permitAll()
				// 其余所有请求,登录控制,创建UsernamePasswordAuthenticationFilter
		        .anyRequest().authenticated()
				.and().formLogin()
				// 登出控制,自定义myLogoutHandler,clearAuthentication清除上下文
				.and().logout().addLogoutHandler(myLogoutHandler).logoutSuccessUrl("/").clearAuthentication(true)
				// 登录拦截器处理
				.and().addFilter(new JWTLoginFilter(authenticationManager(), redisUtils, userService, projectConfig))
				// 请求授权拦截器处理
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), redisUtils, projectConfig))
				// 处理权限不足的情况
		        .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);
				
		
	}
	
	@Override
	public void configure(WebSecurity  web) throws Exception {
		//解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/**.html");
        web.ignoring().antMatchers("/druid/**");
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html**",
                "/webjars/**",
                "/csrf",
                "/");
        web.ignoring().antMatchers("/zuul/video/uploadFile");
        web.ignoring().antMatchers("/video/findVideosByType");
        web.ignoring().antMatchers("/video/indexFindVideosByType");
        web.ignoring().antMatchers("/video/findRelateVideos");
        web.ignoring().antMatchers("/video/findVideosById");
        web.ignoring().antMatchers("/video/getVideoCommentsByid");
	}
	
	
	/**
	 * 授权验证的加密规则
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Autowired
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}