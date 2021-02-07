package dao.bookAlterPanel;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName alterBooks
 * @Description TODO
 * @Author
 * @Date 2021/2/6
 **/
public class alterBooks {
    /**
     * 图书的修改
     * @return
     */
    public boolean alterBs (String id ,String name, String author, String desc, String price , String gender, String type){
        String sql = "update book set  bookName = ?,bookauth = ?,gender = ?,bookPrice = ?,bookDesc = ?, bookType = ? where id = ?";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1,name);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,price);
            preparedStatement.setString(5,desc);
            preparedStatement.setString(6,type);
            preparedStatement.setString(7, id);
            int i = preparedStatement.executeUpdate();
            if (i ==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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
        alterBooks alterBooks = new alterBooks();
        alterBooks.alterBs("6","生物化学","周健","关于生物的化学知识","20.0","1","化学类");
    }
}
