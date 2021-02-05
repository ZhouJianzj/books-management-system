package dao.alterBookTypeManager;

import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName CheckExist
 * @Description TODO
 * @Author
 * @Date 2021/2/5
 **/
public class CheckExist {
    public boolean checkDeleteData(JTextField id){
        String sql = "select * from book where bookType = (select bookType from booktype where id = ?)";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id.getText());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
