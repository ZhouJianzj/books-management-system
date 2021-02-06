package dao.bookAlterPanel;


import utils.JdbcUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 * @ClassName SearchBooks
 * @Description TODO
 * @Author
 * @Date 2021/2/5
 **/
public class SearchBooks {
    /**
     *
     * @param type  下拉框选择的值
     * @param table
     */
    public void searchFrom(String type,String name,String author, JTable table) {
        DefaultTableModel  model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        String sql = "select * from book where bookType = ";
        Statement statement = null;
        ResultSet resultSet = null;
        String x = "女";
        try {
           statement = connection.createStatement();
            sql = sql + "'" + type + "'";
            if (!name.isEmpty()){
                sql = sql + "and bookName = '" + name + "'";
            }
            if (!author.isEmpty()){
                sql =sql + "and bookauth = '" + author + "'";
            }
            resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                Vector vector = new Vector();
                vector.addElement(resultSet.getString(1));
                vector.addElement(resultSet.getString(2));
                vector.addElement(resultSet.getString(3));
                if (resultSet.getString(4).equals("1")){
                    x = "男";
                }
                vector.addElement(x);
                vector.addElement(resultSet.getString(5));
                vector.addElement(resultSet.getString(6));
                vector.addElement(resultSet.getString(7));
                model.addRow(vector);
                x = "女";
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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            initJdbcUtil.closeConnection();
        }

    }
}
