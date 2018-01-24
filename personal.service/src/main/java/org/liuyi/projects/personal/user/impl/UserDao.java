package org.liuyi.projects.personal.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.liuyi.projects.personal.domain.User;
import org.liuyi.projects.personal.user.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao implements IUserDao {
	@Autowired
	protected SqlSession sqlSession;

	@Override
	public User getUser() {
		System.out.println("ok不");
		return sqlSession.selectOne("get");
	}
	

}
