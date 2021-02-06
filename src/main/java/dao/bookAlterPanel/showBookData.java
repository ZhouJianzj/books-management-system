package dao.bookAlterPanel;

import utils.JdbcUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ValidationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * @ClassName showBookData
 * @Description TODO
 * @Author
 * @Date 2021/2/6
 **/
public class showBookData {
    public void flushData(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String sql = "select * from book ";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String x = "女";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Vector vector = new Vector();
                vector.addElement(resultSet.getString(1));
                vector.addElement(resultSet.getString(2));
                vector.addElement(resultSet.getString(3));
                if (resultSet.getString(4).equals("1")){
                    x = "男";
                    System.out.println(resultSet.getString(4));
                }
                vector.addElement(x);
                vector.addElement(resultSet.getString(5));
                vector.addElement(resultSet.getString(6));
                vector.addElement(resultSet.getString(7));
                model.addRow(vector);
                x ="女";
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
