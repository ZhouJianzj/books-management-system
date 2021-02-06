/*
 * Created by JFormDesigner on Wed Feb 03 11:46:40 CST 2021
 */

package view.mainPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class aboutUs extends JFrame {
    public aboutUs() {
        initComponents();
    }

    private void label1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String url = "https://github.com/ZhouJianzj";
        try {
            Runtime.getRuntime().exec("explorer " + url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/pic/login/guanliyuannan.png")).getImage());
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u8be6\u60c5\u70b9\u51fb\uff1ahttps://github.com/ZhouJianzj");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label1MouseClicked(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(140, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(189, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new aboutUs().setVisible(true);
    }
}
