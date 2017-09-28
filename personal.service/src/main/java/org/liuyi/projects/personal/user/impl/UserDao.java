package org.liuyi.projects.personal.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.liuyi.projects.personal.domain.User;
import org.liuyi.projects.personal.user.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements IUserDao  {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public User getUser() {
		Map<String,String> params=new HashMap<String,String>(); 
		params.put("id","8455acdb-84c6-4cd0-b247-8e351e172b64");
		return sqlSession.selectOne("get",params);
	}
	

}
