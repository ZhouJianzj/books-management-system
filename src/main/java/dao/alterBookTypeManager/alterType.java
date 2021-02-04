package dao.alterBookTypeManager;

import sun.net.ConnectionResetException;
import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName alterType
 * @Description TODO
 * @Author
 * @Date 2021/2/4
 **/
public class alterType {
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

    public static void main(String[] args) {
        JTextField jTextField = new JTextField("审计类");
        JTextArea jTextAre = new JTextArea("关于审计的相关知识书籍");
        JTextField jTextField1 = new JTextField("2");
        alterType alterType = new alterType();
        System.out.println(alterType.alterData(jTextField1, jTextField, jTextAre));
    }
}
