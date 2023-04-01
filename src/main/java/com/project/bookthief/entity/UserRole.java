package com.project.bookthief.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@NamedNativeQueries({@NamedNativeQuery(name="userRoleQuery",query = "insert into user_role values(?1,?2)")})
public class UserRole {
	@Id
	@Column(name="user_id")
	int userId;
	@Column(name="role_id")
	int roleId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
