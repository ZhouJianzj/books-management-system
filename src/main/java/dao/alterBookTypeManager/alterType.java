package dao.alterBookTypeManager;

import sun.net.ConnectionResetException;
import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName alterType
 * @Description TODO
 * @Author
 * @Date 2021/2/4
 **/
public class alterType {
    /**
     * 维护图书类
     * @param id
     * @param type
     * @param desc
     * @return
     */
    public int alterData(JTextField id,JTextField type,JTextArea desc){
        int i = 0;
        String sql = "update booktype set bookType = ?,bookDesc = ? where id = ?";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,type.getText());
            preparedStatement.setString(2,desc.getText());
            preparedStatement.setString(3,id.getText());
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initJdbcUtil.closeConnection();
        }
        return i;
    }

    /**
     * 判断修改之后的类别是否存在
     *
     *
     */
    public boolean checkRepet(JTextField type){
        String text = type.getText();
        String sql= "select * from booktype where bookType = ?";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initJdbcUtil.closeConnection();
        }

        return false;
    }

    public static void main(String[] args) {
        JTextField jTextField = new JTextField("审计类");
        JTextArea jTextAre = new JTextArea("关于审计的相关知识书籍");
        JTextField jTextField1 = new JTextField("2");
        alterType alterType = new alterType();
        System.out.println(alterType.alterData(jTextField1, jTextField, jTextAre));
    }
}
