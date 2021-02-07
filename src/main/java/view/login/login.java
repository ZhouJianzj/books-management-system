/*
 * Created by JFormDesigner on Tue Feb 02 17:34:07 CST 2021
 */

package view.login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.LoginDao;

import model.User;
import view.mainPanel.mainPanel;


/**
 * @author unknown
 */
public class login {
    public login() {
        initComponents();
    }

    /**
     * 登录按钮事件
     * @param e
     */
    private void logActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (userName.getText() == null ||"".equals(userName.getText())){
            JOptionPane.showMessageDialog(userName.getParent(),"请输入用户名");
        }else if(password.getPassword().length == 0){
            JOptionPane.showMessageDialog(userName.getParent(),"请输入密码！");
        }else{
            User user = new User();
            user.setUserName(userName.getText());
            user.setPassword(String.valueOf(password.getPassword()));
            LoginDao loginDao = new LoginDao();
            boolean checked = loginDao.checked(user);
            if (checked){
                new mainPanel().carousel();
                login.dispose();
            }else{
                JOptionPane.showMessageDialog(userName.getParent(),"你输入的账户密码错误！");
            }
        }
    }

    /**
     * 重置事件
     * @param e
     */
    private void resetActionPerformed(ActionEvent e) {
        // TODO add your code here
        userName.setText("");
        password.setText("");
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        login = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        userName = new JTextField();
        password = new JPasswordField();
        log = new JButton();
        reset = new JButton();

        //======== login ========
        {
            login.setBackground(Color.lightGray);
            login.setTitle("\u767b\u5f55");
            login.setVisible(true);
            login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            login.setResizable(false);
            login.setIconImage(new ImageIcon(getClass().getResource("/pic/login/guanliyuannan.png")).getImage());
            Container loginContentPane = login.getContentPane();

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
            label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 24));
            label1.setHorizontalAlignment(SwingConstants.RIGHT);
            label1.setIcon(new ImageIcon(getClass().getResource("/pic/login/tushuguan.png")));

            //---- label2 ----
            label2.setText("\u7528\u6237\uff1a");
            label2.setIcon(new ImageIcon(getClass().getResource("/pic/login/xiaoren.png")));
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");
            label3.setIcon(new ImageIcon(getClass().getResource("/pic/login/yuechi.png")));
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

            //---- userName ----
            userName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            userName.setHorizontalAlignment(SwingConstants.CENTER);

            //---- password ----
            password.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
            password.setHorizontalAlignment(SwingConstants.CENTER);

            //---- log ----
            log.setText("\u767b\u5f55");
            log.setIcon(new ImageIcon(getClass().getResource("/pic/login/denglu.png")));
            log.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
            log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logActionPerformed(e);
                }
            });

            //---- reset ----
            reset.setText("\u91cd\u7f6e");
            reset.setIcon(new ImageIcon(getClass().getResource("/pic/login/zhongzhi.png")));
            reset.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
            reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetActionPerformed(e);
                }
            });

            GroupLayout loginContentPaneLayout = new GroupLayout(loginContentPane);
            loginContentPane.setLayout(loginContentPaneLayout);
            loginContentPaneLayout.setHorizontalGroup(
                loginContentPaneLayout.createParallelGroup()
                    .addGroup(loginContentPaneLayout.createSequentialGroup()
                        .addGroup(loginContentPaneLayout.createParallelGroup()
                            .addGroup(loginContentPaneLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(loginContentPaneLayout.createSequentialGroup()
                                        .addComponent(reset, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(log, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(loginContentPaneLayout.createSequentialGroup()
                                        .addGroup(loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label2)
                                            .addComponent(label3))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(loginContentPaneLayout.createParallelGroup()
                                            .addComponent(userName, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(loginContentPaneLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(141, Short.MAX_VALUE))
            );
            loginContentPaneLayout.setVerticalGroup(
                loginContentPaneLayout.createParallelGroup()
                    .addGroup(loginContentPaneLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(54, 54, 54)
                        .addGroup(loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(45, 45, 45)
                        .addGroup(loginContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(reset)
                            .addComponent(log))
                        .addContainerGap(72, Short.MAX_VALUE))
            );
            login.setSize(525, 430);
            login.setLocationRelativeTo(login.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame login;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField userName;
    private JPasswordField password;
    private JButton log;
    private JButton reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        login login = new login();
    }
}
