package utils;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @ClassName jdbcFengZhuang
 * @Description TODO
 * @Author
 * @Date 2021/2/26
 **/
public class jdbcFengZhuang {

    /**
     * @ClassName jdbcFengZhuang
     * @Description TODO
     * @Author
     * @Date 2021/2/26
     **/

//jdbc的工具类封装

        //    需要的全局的变量
        private Connection con = null;
        private PreparedStatement pre = null;

        //    首先注册
        static {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        /**
         获取连接
         */
        public Connection getConnection(){
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhoujian","root","123321");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return con;
        }
        /**
         创建搬运工具
         */
        public PreparedStatement createPreparement(String sql){
            if (con != null){
                try {
                    con.prepareStatement(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("没有创建连接");
            }
            return pre;
        }

        /**
         关闭 连接 、搬运工具
         */
        public void closeData(){
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public void closeData(Resultset resultset){
//            try {
//                if (resultset != null) {
//
////                    resultset.close();
//                }
//            }catch (SQLException  e){
//
//            }finally {

                closeData();
//            }
        }

    }


