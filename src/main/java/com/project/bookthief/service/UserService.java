package com.project.bookthief.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.bookthief.entity.User;
import com.project.bookthief.entity.UserRole;
import com.project.bookthief.repository.UserRepository;
import com.project.bookthief.repository.UserRoleRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	SessionFactory sessionFactory;

	public boolean userLogin(@ModelAttribute("user") User user) {
		Optional<User> optional = userRepository.findUserByEmail(user.getEmail());
		if (optional.isPresent()) {
			User objUser = optional.get();
			if (objUser.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public String addUser(@ModelAttribute("user") User user) {
		User user2 = new User();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedNativeQuery("userRoleQuery");
		BeanUtils.copyProperties(user, user2);
		userRepository.save(user2);
		query.setParameter(1, user2.getId());
		query.setParameter(2, 2);
		query.executeUpdate();
		tx.commit();
		return "Success";
	}

	public int userRole(int userId) {
		Optional<UserRole> optional = userRoleRepository.findById(userId);
		if (optional.isPresent()) {
			UserRole userRole = optional.get();
			return userRole.getRoleId();
		}
		return 0;
	}

	public int getUserById(String email) {
		Optional<User> optional = userRepository.findUserByEmail(email);
		if (optional.isPresent()) {
			User user = optional.get();
			return user.getId();
		}
		return 0;
	}

}
