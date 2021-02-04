package dao.alterBookTypeManager;

import utils.JdbcUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * @ClassName showTableData
 * @Description TODO
 * @Author
 * @Date 2021/2/4
 **/

/**
 * 在表格中展示所以的图书类别
 */
public class showTableData {
    /**
     * 返回一个结果集合用来判断是否库里有数据，这里需要注意的是结果集合再关闭前是不可以使用的
     * 传递过来一个表格
     * @param alterPanelTable
     * @return
     */
    public ResultSet showTableData(JTable alterPanelTable){
        DefaultTableModel model = (DefaultTableModel)alterPanelTable.getModel();
        model.setRowCount(0);
        String sql = "select * from booktype ";
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
           statement = connection.createStatement();
           resultSet = statement.executeQuery(sql);
            try {
                while (resultSet.next()){
                    Vector vector = new Vector();
                    vector.addElement(resultSet.getString("id"));
                    vector.addElement(resultSet.getString("bookType"));
                    vector.addElement(resultSet.getString("bookDesc"));
                    model.addRow(vector);
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

            }
            initJdbcUtil.closeConnection();
        }
        return  resultSet;
    }

    public static void main(String[] args) {

    }
}
