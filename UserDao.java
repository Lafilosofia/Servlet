package dao;



import java.util.List;

import entity.User;

public interface UserDao {
	//����û�
	 int addUser(User user);
	 //��ѯ�����û�����
	 List <User> findUserAll();
	 //����idɾ���û����� 
	 int delUser(int id);
	 //����id�޸��û�����
	 int updateUser(int id);
}
