/*
 * Created by JFormDesigner on Sun Feb 07 15:31:53 CST 2021
 */

package view.login;

import dao.LoginDao;
import model.User;
import view.mainPanel.mainPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
    }

    private void loginActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (userName.getText() == null ||"".equals(userName.getText())){
            JOptionPane.showMessageDialog(login,"请输入用户名");
            System.out.println(String.valueOf(password.getPassword()));
        }else if(String.valueOf(password.getPassword()) == null||"".equals(String.valueOf(password.getPassword()))){
            JOptionPane.showMessageDialog(login,"请输入密码！");
        }else{
            User user = new User();
            user.setUserName(userName.getText());
            user.setPassword(String.valueOf(password.getPassword()));
            LoginDao loginDao = new LoginDao();
            boolean checked = loginDao.checked(user);
            if (checked){
                new mainPanel().carousel();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(login,"你输入的账户密码错误！");
            }
        }
    }

    private void resertActionPerformed(ActionEvent e) {
        // TODO add your code here
        userName.setText("");
        password.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ResourceBundle bundle = ResourceBundle.getBundle("view");
        label1 = new JLabel();
        label2 = new JLabel();
        userName = new JTextField();
        password = new JPasswordField();
        resert = new JButton();
        login = new JButton();
        label3 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/pic/login/guanliyuannan.png")).getImage());
        setTitle("\u7ba1\u7406\u5458\u767b\u5f55");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText(bundle.getString("label1.text_2"));
        label1.setIcon(new ImageIcon(getClass().getResource("/pic/login/xiaoren.png")));

        //---- label2 ----
        label2.setText(bundle.getString("label2.text_2"));
        label2.setIcon(new ImageIcon(getClass().getResource("/pic/login/yuechi.png")));

        //---- userName ----
        userName.setHorizontalAlignment(SwingConstants.CENTER);

        //---- password ----
        password.setHorizontalAlignment(SwingConstants.CENTER);

        //---- resert ----
        resert.setText(bundle.getString("resert.text"));
        resert.setIcon(new ImageIcon(getClass().getResource("/pic/login/zhongzhi.png")));
        resert.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resertActionPerformed(e);
            }
        });

        //---- login ----
        login.setText(bundle.getString("login.text"));
        login.setIcon(new ImageIcon(getClass().getResource("/pic/login/denglu.png")));
        login.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });

        //---- label3 ----
        label3.setText(bundle.getString("label3.text_2"));
        label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 22));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setIcon(new ImageIcon(getClass().getResource("/pic/login/tushuguan.png")));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(resert)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                    .addComponent(login)
                    .addGap(95, 95, 95))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(107, 107, 107)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userName, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(152, 152, 152)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(135, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                    .addGap(41, 41, 41)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(45, 45, 45)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(67, 67, 67)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(resert)
                        .addComponent(login))
                    .addContainerGap(64, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    private JTextField userName;
    private JPasswordField password;
    private JButton resert;
    private JButton login;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new Login();
    }
}
