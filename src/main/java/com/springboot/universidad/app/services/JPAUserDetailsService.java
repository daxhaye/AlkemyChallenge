package com.springboot.universidad.app.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.universidad.app.models.dao.IStudentDao;
import com.springboot.universidad.app.models.entity.Role;
import com.springboot.universidad.app.models.entity.Student;

@Service("jpaUserDetailsService")
public class JPAUserDetailsService implements UserDetailsService{

	@Autowired
	private IStudentDao studentDao;
	
	private Logger logger = LoggerFactory.getLogger(JPAUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentDao.findByUsername(username);
		
		if (student == null) {
			logger.error("Error login: no existe el usuario '"+ username +"'");
			throw new UsernameNotFoundException("Username " + username + "no existe en el sistema!");
		} else {
			logger.info(student.getUsername() + " " + student.getName());
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role: student.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if (authorities.isEmpty()) {
			logger.error("Error login: no existe el usuario '"+ username +"' no tiene roles asignados");
			throw new UsernameNotFoundException("Error login: no existe el usuario '"+ username +"' no tiene roles asignados");
		}
		
		return new User(student.getUsername(), student.getPassword(), true, true, true, true, authorities);
	}

}
