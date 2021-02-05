package dao.booAddPanel;

import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

/**
 * @ClassName getJComboxData
 * @Description TODO
 * @Author
 * @Date 2021/2/5
 **/
public class getJComboxData {

    /**
     * 获取下拉列表的数据
     * @param jComboBox
     */
    public void getData(JComboBox jComboBox){
        String sql  = "select bookType  from booktype  ";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sql);
             while (resultSet.next()){
                 jComboBox.addItem(resultSet.getString("bookType"));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initJdbcUtil.closeConnection();
        }
        }

    public static void main(String[] args) {
        new getJComboxData().getData(new JComboBox());
    }

    }

