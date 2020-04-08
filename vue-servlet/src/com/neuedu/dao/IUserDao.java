package com.neuedu.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.exception.DbException;
import com.neuedu.pojo.User;

public interface IUserDao {

	List<User> findAll()throws DbException, SQLException, ClassNotFoundException;

	int count()throws DbException, SQLException, ClassNotFoundException;

	List<User> findAll(int pageSize, int currentPage)throws DbException, SQLException, ClassNotFoundException;

	User getUserById(int id)throws DbException, SQLException, ClassNotFoundException;

	int update(User user)throws DbException, SQLException, ClassNotFoundException;

}
