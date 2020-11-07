package dao;



import java.util.List;

import entity.User;

public interface UserDao {
	//添加用户
	 int addUser(User user);
	 //查询所有用户数据
	 List <User> findUserAll();
	 //根据id删除用户数据 
	 int delUser(int id);
	 //根据id修改用户数据
	 int updateUser(int id);
}
