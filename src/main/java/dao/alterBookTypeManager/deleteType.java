package dao.alterBookTypeManager;

import utils.JdbcUtil;

import javax.lang.model.element.VariableElement;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName deleteType
 * @Description TODO
 * @Author
 * @Date 2021/2/4
 **/
public class deleteType {
    /**
     * 删除图书类别的方法
     * alterPanelID的传递
     * 返回执行的结果
     * @param jTextField
     * @return
     */
    public int deleteData(JTextField jTextField){
        int i = 0;
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        String sql = "delete from booktype where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,jTextField.getText());
            i = preparedStatement.executeUpdate();
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
        return i;
    }

    public static void main(String[] args) {
        deleteType deleteType = new deleteType();
        JTextField jTextField = new JTextField();
        jTextField.setText("4");
        System.out.println(deleteType.deleteData(jTextField));
    }
}
