package dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;


import entity.User;
import util.DButil;

public class UserDaoImpl implements UserDao{
	//用户注册
	public int addUser(User user){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DButil.getConnection();
			String sql = "insert into usertable values(null,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,user.getUserName());
			preparedStatement.setString(2, user.getUserPwd());
			preparedStatement.setString(3, user.getUserEmail());
			preparedStatement.setString(4, user.getUserPhone());
			int n = preparedStatement.executeUpdate();
			return n; 

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
		return 0;
	}
	
	//查询所有用户数据
	public List<User> findUserAll(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			List list = new ArrayList<User>();
			connection = DButil.getConnection();
			String sql = "select * from usertable";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getNString("username"));
				user.setUserPwd(resultSet.getNString("userpassword"));
				user.setUserPhone(resultSet.getString("userphone"));
				user.setUserEmail(resultSet.getString("usermail"));
				list.add(user);
			}
					return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}

	//根据id删除用户数据
	@Override
	public int delUser(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DButil.getConnection();
			String sql = "delete from usertable where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				System.out.println("删除成功!");
			}else {
				throw new RuntimeException("删除失败");
			}
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return 0;
	}

	@Override
	public int updateUser(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DButil.getConnection();
			String sql = "select * from usertable where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("username"));
				user.setUserPwd(resultSet.getString("userpassword"));
				user.setUserPhone(resultSet.getString("userphone"));
				user.setUserEmail(resultSet.getString("useremail"));
				String sql2 = "update usertable set username = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				int n = preparedStatement2.executeUpdate();
				if (n > 0) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return 0;
	}
	
	
}
