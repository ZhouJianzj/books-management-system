package dao.booAddPanel;

import model.Book;
import utils.JdbcUtil;

import javax.swing.*;
import java.security.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @ClassName addBookDoAdd
 * @Description TODO
 * @Author zhoujian
 * @Date 2021/2/5
 **/
public class addBookDoAdd {
    /**
     * 添加前检测图书是否已存在
     *

      * @return
     */
    public boolean doAddCheck(Book book){
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        String sql = "select * from book where bookName = ? and bookauth = ? and gender = ? and bookType = ? and bookDesc = ? and bookPrice = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
          preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setString(1,book.getBookName());
          preparedStatement.setString(2,book.getBookauthor());
          preparedStatement.setString(3,book.getGender());
          preparedStatement.setString(4,book.getBookType());
          preparedStatement.setString(5,book.getBookDesc());
          preparedStatement.setString(6,String.valueOf(book.getBookPrice()));
           resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
              return false;
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
        return true;
    }

    /**
     * 添加图书
     * @return
     */
    public boolean doAdd(Book book){

        String sql = "insert into book(bookName,bookauth,gender,bookPrice,bookType,bookDesc) values(?,?,?,?,?,?)";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,book.getBookName());
           preparedStatement.setString(2,book.getBookauthor());
           preparedStatement.setString(3,book.getGender());
           preparedStatement.setString(4,String.valueOf(book.getBookPrice()));
           preparedStatement.setString(5,book.getBookType());
           preparedStatement.setString(6,book.getBookDesc());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
        return false;
    }
    public static void main(String[] args) {

    }
}
