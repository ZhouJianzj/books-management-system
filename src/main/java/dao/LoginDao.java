package dao;


import model.User;
import utils.JdbcUtil;

import java.sql.*;

/**
 * @ClassName LoginDao
 * @Description TODO
 * @Author
 * @Date 2021/2/2
 **/

/**
 * 登录验证
 */
public class LoginDao {
    public boolean checked(User user){
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection con = initJdbcUtil.getConnection();
        String sql= new String("select userName,password from login where userName =? and password=?");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
           statement = con.prepareStatement(sql);
           statement.setString(1,user.getUserName());
           statement.setString(2,user.getPassword());
           resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initJdbcUtil.closeConnection();
        }
        return  false;
    }

    public static void main(String[] args) {
        LoginDao loginDao = new LoginDao();
        User user = new User();
        user.setUserName("zhoujian");
        user.setPassword("123321");
        loginDao.checked(user);
    }
}
