package com.itcast.dw.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itcast.dw.model.User;
import com.itcast.dw.service.UserService;

/**
 * UserDetailsService实现类
 */
@Service
public class JWTUserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User loginUser = userService.getUserByName(account);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>loginUser = " + loginUser);
		if (loginUser == null) {
			throw new UsernameNotFoundException(String.format("找不到账户 '%s'.", account));
		} else {
			// TODO 查询权限列表放入 查询用户对应的角色id
			List<String> list = new ArrayList<>();
			return new JWTUser(loginUser.getUsername(), bCryptPasswordEncoder.encode(loginUser.getPassword()),
					list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		}
	}

}
