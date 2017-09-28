package org.liuyi.projects.personal.user.impl;

import org.liuyi.projects.personal.domain.User;
import org.liuyi.projects.personal.user.IUserDao;
import org.liuyi.projects.personal.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService implements IUserService{
	@Autowired
	private IUserDao userDao;
	@Override
	public User getUser() {
		return userDao.getUser();
	}

}
