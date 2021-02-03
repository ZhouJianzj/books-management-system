package dao.bookTypeManager;

import lombok.Getter;
import model.BookType;
import utils.JdbcUtil;

import javax.lang.model.element.VariableElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName bookTypeAdd
 * @Description TODO
 * @Author
 * @Date 2021/2/3
 **/
@Getter
public class bookTypeAdd {
    private  int resultNum = 0;
    public bookTypeAdd(){}
    public bookTypeAdd(BookType bookType){
        JdbcUtil initJdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = initJdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into booktype(bookType,bookDesc)values(?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bookType.getBookType());
            preparedStatement.setString(2,bookType.getBookDesc());
            resultNum =  preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
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
        BookType b = new BookType();
        b.setBookType("审计类");
        b.setBookDesc("关于初级会计师的相关书籍");
        bookTypeAdd bookTypeAdd = new bookTypeAdd(b);
        System.out.println(bookTypeAdd.getResultNum());
    }
}
