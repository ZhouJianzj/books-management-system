package dao.alterBookTypeManager;

import utils.JdbcUtil;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 * @ClassName alterSearchButton
 * @Description TODO
 * @Author
 * @Date 2021/2/4
 **/
public class alterSearch {
    /**
     * 获取搜索的文字进行模糊查询
     * 首先重置表格的行数，在传递搜索到的数据
     * @param jTextField
     * @param jTable
     * @return
     */
    public ResultSet doSearch(JTextField jTextField,JTable jTable){
        DefaultTableModel defaultTableModel = (DefaultTableModel)jTable.getModel();
        defaultTableModel.setRowCount(0);
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        StringBuffer sql = new StringBuffer("select * from  booktype where bookType like '");
        sql.append(jTextField.getText() + "%" + "'");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(String.valueOf(sql));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Vector vector = new Vector();
                vector.addElement(resultSet.getString("id"));
                vector.addElement(resultSet.getString("bookType"));
                vector.addElement(resultSet.getString("bookDesc"));
                defaultTableModel.addRow(vector);
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
        return resultSet;
    }

    public static void main(String[] args) {
        alterSearch alterSearch = new alterSearch();
        JTextField jTextField = new JTextField();
        jTextField.setText("计");
        JTable jTable = new JTable(5, 5);
        alterSearch.doSearch(jTextField,jTable);
    }
}
