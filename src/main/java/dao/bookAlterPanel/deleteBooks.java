package dao.bookAlterPanel;

import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName deleteBooks
 * @Description TODO
 * @Author
 * @Date 2021/2/6
 **/
public class deleteBooks {
    /**
     * 图书删除方法
     * @param id
     * @return
     */
    public boolean deleteData(JTextField id){
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        String sql = "delete from book where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id.getText());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return  true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            initJdbcUtil.getConnection();
        }
        return false;
    }

    public static void main(String[] args) {
        deleteBooks deleteBooks = new deleteBooks();
        deleteBooks.deleteData(new JTextField("1"));

    }
}
