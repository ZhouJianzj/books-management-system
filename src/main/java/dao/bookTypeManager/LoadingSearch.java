package dao.bookTypeManager;

import lombok.Getter;
import utils.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName LoadingSearch
 * @Description TODO
 * @Author
 * @Date 2021/2/3
 **/
@Getter
public class LoadingSearch {
    private Boolean result = false;
    public LoadingSearch(){ }

    /**
     *传递搜索的文字
     * @param test
     */
    public LoadingSearch(String test){
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        String sql = new String("select id,bookType,bookDesc from bookType where bookType = ?");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,test);
            resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
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

    }

    public static void main(String[] args) {
        LoadingSearch loadingSearch = new LoadingSearch("电气类");
        System.out.println(loadingSearch.result);
    }
}
