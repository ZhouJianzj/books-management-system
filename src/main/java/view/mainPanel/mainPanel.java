/*
 * Created by JFormDesigner on Wed Feb 03 11:20:51 CST 2021
 */

package view.mainPanel;

import java.beans.*;
import dao.bookTypeManager.LoadingSearch;
import dao.bookTypeManager.bookTypeAdd;
import model.BookType;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.Timer;

/**
 * @author unknown
 */
public class mainPanel {
    public mainPanel() {
        initComponents();

    }
    public void setBg(){
    }

    /**
     * 图书类别添加事件
     * @param e
     */
    private void addMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        cut("card2");
    }

    /**
     * 图书类别维护事件
     * @param e
     */
    private void alterMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        cut("card3");
    }

    /**
     * 图书添加事件
     * @param e
     */
    private void bookAddMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        cut("card4");
    }

    /**
     * 图书维护事件
     * @param e
     */
    private void bookAlterMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        cut("card5");
    }

    /**
     * 安全退出事件
     * @param e
     */
    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    /**
     * 关于我们事件
     * @param e
     */
    private void menuItem6ActionPerformed(ActionEvent e) {
        // TODO add your code here
        aboutUs aboutUs = new aboutUs();
        aboutUs.setVisible(true);
        aboutUs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 切换panel的方法
     */
    private  void  cut(String name){
        //涉及到向下转型，避免了每次都要写
        Container contentPane = mianForm.getContentPane();
        CardLayout cardLayout;
        LayoutManager layout = contentPane.getLayout();
        cardLayout = (CardLayout) layout;
        cardLayout.show(addPanel.getParent(),name);
    }

    /**
     * 图书类别panel中clean按钮事件
     * @param e
     */
    private void cleanActionPerformed(ActionEvent e) {
        // TODO add your code here
        bookTpye.setText("");
        bookDesc.setText("");
        search.setText("");
    }

    /**
     * 图书类别panel中添加按钮事件
     * @param e
     */
    private void bookTypeAddActionPerformed(ActionEvent e) {
        // TODO add your code here
        //对输入框的文字进行判空处理
        String typeText = bookTpye.getText();
        String descText = bookDesc.getText();
        if ("".equals(typeText) || typeText == null){
            JOptionPane.showMessageDialog(addPanel,"请输入类别名！");
        }else if("".equals(descText) || descText == null){
            JOptionPane.showMessageDialog(addPanel,"请输入类别描述！");
        }else{
            //当没有进行搜索判重复的时候，图书类别名称栏自动判断
            LoadingSearch loadingSearch = new LoadingSearch(typeText);
            Boolean result = loadingSearch.getResult();
            if (result){
                JOptionPane.showMessageDialog(search.getParent(),"有重复的图书类别，不能重复添加！");
            }else{
                BookType bookType = new BookType();
                bookType.setBookType(typeText);
                bookType.setBookDesc(descText);
                bookTypeAdd bookTypeAdd = new bookTypeAdd(bookType);
                if (bookTypeAdd.getResultNum() == 1){
                    JOptionPane.showMessageDialog(addPanel,"添加成功！");
                }
            }
        }
    }

    /**
     * 图书类别panel中搜索按钮事件
     * * @param e
     */
    private void loadingSearchActionPerformed(ActionEvent e) {
        // TODO add your code here
        String text = search.getText();
        if("".equals(text) || text == null) {
            JOptionPane.showMessageDialog(addPanel, "请输入类别描述！");
        }else{
            LoadingSearch loadingSearch = new LoadingSearch(text);
            Boolean result = loadingSearch.getResult();
            if (result){
                JOptionPane.showMessageDialog(search.getParent(),"有重复的图书类别，不能重复添加！");
            }else{
                JOptionPane.showMessageDialog(search.getParent(),"没有重复的类别，可以添加！");
                bookTpye.setText(text);
            }
        }

    }

    private void picPanelPropertyChange(PropertyChangeEvent e) {
        // TODO add your code here
        //定时器切换JPanel
        new Timer(2000,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                LayoutManager layout = picPanel.getLayout();
                CardLayout cardLayout;
                cardLayout = (CardLayout)layout;
                cardLayout.next(picPanel);
            }
        }).start();

    }

    private void exitActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 0：代表的是确认 1:代表的是否 2：代表的是取消
        int i = JOptionPane.showConfirmDialog(null, "是否确认退出！");
        if (i == 0){
            mianForm.dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        ResourceBundle bundle = ResourceBundle.getBundle("view");
        mianForm = new JFrame();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menu2 = new JMenu();
        addMenu = new JMenuItem();
        alterMenu = new JMenuItem();
        menu3 = new JMenu();
        bookAddMenu = new JMenuItem();
        bookAlterMenu = new JMenuItem();
        exit = new JMenuItem();
        menu4 = new JMenu();
        menuItem6 = new JMenuItem();
        welcome = new JPanel();
        label5 = new JLabel();
        addPanel = new JPanel();
        label4 = new JLabel();
        label6 = new JLabel();
        bookTpye = new JTextField();
        scrollPane1 = new JScrollPane();
        bookDesc = new JTextArea();
        clean = new JButton();
        add = new JButton();
        search = new JTextField();
        loadingSearch = new JButton();
        picPanel = new JPanel();
        picPanelFirst = new JPanel();
        one = new JLabel();
        picPanelTwo = new JPanel();
        two = new JLabel();
        picPanelThree = new JPanel();
        three = new JLabel();
        alterPanel = new JPanel();
        bookAddPanel = new JPanel();
        label2 = new JLabel();
        bookAlterPanel = new JPanel();
        label1 = new JLabel();

        //======== mianForm ========
        {
            mianForm.setVisible(true);
            mianForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mianForm.setTitle("BOOKS-MANAGEMENT-SYSTEM");
            mianForm.setResizable(false);
            Container mianFormContentPane = mianForm.getContentPane();
            mianFormContentPane.setLayout(new CardLayout());

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText(bundle.getString("menu1.text"));
                    menu1.setIcon(new ImageIcon(getClass().getResource("/pic/login/banshou.png")));

                    //======== menu2 ========
                    {
                        menu2.setText(bundle.getString("menu2.text"));

                        //---- addMenu ----
                        addMenu.setText(bundle.getString("addMenu.text"));
                        addMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                addMenuActionPerformed(e);
                            }
                        });
                        menu2.add(addMenu);

                        //---- alterMenu ----
                        alterMenu.setText(bundle.getString("alterMenu.text"));
                        alterMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                alterMenuActionPerformed(e);
                            }
                        });
                        menu2.add(alterMenu);
                    }
                    menu1.add(menu2);

                    //======== menu3 ========
                    {
                        menu3.setText(bundle.getString("menu3.text"));

                        //---- bookAddMenu ----
                        bookAddMenu.setText(bundle.getString("bookAddMenu.text"));
                        bookAddMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bookAddMenuActionPerformed(e);
                            }
                        });
                        menu3.add(bookAddMenu);

                        //---- bookAlterMenu ----
                        bookAlterMenu.setText(bundle.getString("bookAlterMenu.text"));
                        bookAlterMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                bookAlterMenuActionPerformed(e);
                            }
                        });
                        menu3.add(bookAlterMenu);
                    }
                    menu1.add(menu3);

                    //---- exit ----
                    exit.setText(bundle.getString("exit.text"));
                    exit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuItem3ActionPerformed(e);
                            exitActionPerformed(e);
                        }
                    });
                    menu1.add(exit);
                }
                menuBar1.add(menu1);

                //======== menu4 ========
                {
                    menu4.setText(bundle.getString("menu4.text"));
                    menu4.setIcon(new ImageIcon(getClass().getResource("/pic/login/guanyuwomen.png")));

                    //---- menuItem6 ----
                    menuItem6.setText(bundle.getString("menuItem6.text"));
                    menuItem6.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            menuItem6ActionPerformed(e);
                        }
                    });
                    menu4.add(menuItem6);
                }
                menuBar1.add(menu4);
            }
            mianForm.setJMenuBar(menuBar1);

            //======== welcome ========
            {
                welcome.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border
                .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
                .Color.red),welcome. getBorder()));welcome. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
                propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
                ;}});

                //---- label5 ----
                label5.setText(bundle.getString("label5.text"));
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));

                GroupLayout welcomeLayout = new GroupLayout(welcome);
                welcome.setLayout(welcomeLayout);
                welcomeLayout.setHorizontalGroup(
                    welcomeLayout.createParallelGroup()
                        .addGroup(welcomeLayout.createSequentialGroup()
                            .addGap(185, 185, 185)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(226, Short.MAX_VALUE))
                );
                welcomeLayout.setVerticalGroup(
                    welcomeLayout.createParallelGroup()
                        .addGroup(welcomeLayout.createSequentialGroup()
                            .addGap(121, 121, 121)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(453, Short.MAX_VALUE))
                );
            }
            mianFormContentPane.add(welcome, "card1");

            //======== addPanel ========
            {

                //---- label4 ----
                label4.setText(bundle.getString("label4.text"));
                label4.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                label4.setIcon(new ImageIcon(getClass().getResource("/pic/login/tushu.png")));

                //---- label6 ----
                label6.setText(bundle.getString("label6.text"));
                label6.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                label6.setIcon(new ImageIcon(getClass().getResource("/pic/login/miaoshu.png")));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(bookDesc);
                }

                //---- clean ----
                clean.setText(bundle.getString("clean.text"));
                clean.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                clean.setIcon(new ImageIcon(getClass().getResource("/pic/login/zhongzhi.png")));
                clean.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanActionPerformed(e);
                    }
                });

                //---- add ----
                add.setText(bundle.getString("add.text"));
                add.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                add.setIcon(new ImageIcon(getClass().getResource("/pic/login/jiashang.png")));
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bookTypeAddActionPerformed(e);
                    }
                });

                //---- loadingSearch ----
                loadingSearch.setText(bundle.getString("loadingSearch.text"));
                loadingSearch.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
                loadingSearch.setIcon(new ImageIcon(getClass().getResource("/pic/login/sousuo.png")));
                loadingSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadingSearchActionPerformed(e);
                    }
                });

                //======== picPanel ========
                {
                    picPanel.setBackground(new Color(255, 204, 204));
                    picPanel.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent e) {
                            picPanelPropertyChange(e);
                        }
                    });
                    picPanel.setLayout(new CardLayout());

                    //======== picPanelFirst ========
                    {
                        picPanelFirst.setBackground(new Color(204, 255, 204));
                        picPanelFirst.setLayout(new BorderLayout());

                        //---- one ----
                        one.setText(bundle.getString("one.text"));
                        one.setIcon(new ImageIcon(getClass().getResource("/pic/login/7324741c-7a3d-46cd-b010-2585ae08226b.jpg")));
                        picPanelFirst.add(one, BorderLayout.CENTER);
                    }
                    picPanel.add(picPanelFirst, "card1");

                    //======== picPanelTwo ========
                    {
                        picPanelTwo.setBackground(new Color(255, 204, 204));
                        picPanelTwo.setLayout(new BorderLayout());

                        //---- two ----
                        two.setText(bundle.getString("two.text"));
                        two.setIcon(new ImageIcon(getClass().getResource("/pic/login/6d9c71af-8d82-4b05-b1b9-5544545c2295.jpg")));
                        picPanelTwo.add(two, BorderLayout.CENTER);
                    }
                    picPanel.add(picPanelTwo, "card2");

                    //======== picPanelThree ========
                    {
                        picPanelThree.setBackground(new Color(153, 255, 255));
                        picPanelThree.setLayout(new BorderLayout());

                        //---- three ----
                        three.setText(bundle.getString("three.text"));
                        three.setIcon(new ImageIcon(getClass().getResource("/pic/login/113c0d58-0756-402a-ba9a-6d222e8321ad.jpg")));
                        picPanelThree.add(three, BorderLayout.CENTER);
                    }
                    picPanel.add(picPanelThree, "card3");
                }

                GroupLayout addPanelLayout = new GroupLayout(addPanel);
                addPanel.setLayout(addPanelLayout);
                addPanelLayout.setHorizontalGroup(
                    addPanelLayout.createParallelGroup()
                        .addGroup(addPanelLayout.createSequentialGroup()
                            .addGroup(addPanelLayout.createParallelGroup()
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addGap(240, 240, 240)
                                    .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(addPanelLayout.createParallelGroup()
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bookTpye, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addGap(285, 285, 285)
                                    .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(search, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addPanelLayout.createSequentialGroup()
                                            .addComponent(clean)
                                            .addGap(256, 256, 256)
                                            .addComponent(add)))
                                    .addGap(18, 18, 18)
                                    .addComponent(loadingSearch, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(142, Short.MAX_VALUE))
                        .addComponent(picPanel, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
                );
                addPanelLayout.setVerticalGroup(
                    addPanelLayout.createParallelGroup()
                        .addGroup(addPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(picPanel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68, 68, 68)
                                    .addComponent(search, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addComponent(loadingSearch))
                            .addGap(55, 55, 55)
                            .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(bookTpye, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(addPanelLayout.createParallelGroup()
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(label6)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE))
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addGap(30, 30, 30)))
                            .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(add)
                                .addComponent(clean))
                            .addGap(49, 49, 49))
                );
            }
            mianFormContentPane.add(addPanel, "card2");

            //======== alterPanel ========
            {

                GroupLayout alterPanelLayout = new GroupLayout(alterPanel);
                alterPanel.setLayout(alterPanelLayout);
                alterPanelLayout.setHorizontalGroup(
                    alterPanelLayout.createParallelGroup()
                        .addGap(0, 1018, Short.MAX_VALUE)
                );
                alterPanelLayout.setVerticalGroup(
                    alterPanelLayout.createParallelGroup()
                        .addGap(0, 730, Short.MAX_VALUE)
                );
            }
            mianFormContentPane.add(alterPanel, "card3");

            //======== bookAddPanel ========
            {

                //---- label2 ----
                label2.setText(bundle.getString("label2.text"));

                GroupLayout bookAddPanelLayout = new GroupLayout(bookAddPanel);
                bookAddPanel.setLayout(bookAddPanelLayout);
                bookAddPanelLayout.setHorizontalGroup(
                    bookAddPanelLayout.createParallelGroup()
                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                            .addGap(290, 290, 290)
                            .addComponent(label2)
                            .addContainerGap(680, Short.MAX_VALUE))
                );
                bookAddPanelLayout.setVerticalGroup(
                    bookAddPanelLayout.createParallelGroup()
                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                            .addGap(171, 171, 171)
                            .addComponent(label2)
                            .addContainerGap(542, Short.MAX_VALUE))
                );
            }
            mianFormContentPane.add(bookAddPanel, "card4");

            //======== bookAlterPanel ========
            {

                //---- label1 ----
                label1.setText(bundle.getString("label1.text"));

                GroupLayout bookAlterPanelLayout = new GroupLayout(bookAlterPanel);
                bookAlterPanel.setLayout(bookAlterPanelLayout);
                bookAlterPanelLayout.setHorizontalGroup(
                    bookAlterPanelLayout.createParallelGroup()
                        .addGroup(bookAlterPanelLayout.createSequentialGroup()
                            .addGap(280, 280, 280)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(552, Short.MAX_VALUE))
                );
                bookAlterPanelLayout.setVerticalGroup(
                    bookAlterPanelLayout.createParallelGroup()
                        .addGroup(bookAlterPanelLayout.createSequentialGroup()
                            .addGap(153, 153, 153)
                            .addComponent(label1)
                            .addContainerGap(560, Short.MAX_VALUE))
                );
            }
            mianFormContentPane.add(bookAlterPanel, "card5");
            mianForm.setSize(1020, 800);
            mianForm.setLocationRelativeTo(mianForm.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JFrame mianForm;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem addMenu;
    private JMenuItem alterMenu;
    private JMenu menu3;
    private JMenuItem bookAddMenu;
    private JMenuItem bookAlterMenu;
    private JMenuItem exit;
    private JMenu menu4;
    private JMenuItem menuItem6;
    private JPanel welcome;
    private JLabel label5;
    private JPanel addPanel;
    private JLabel label4;
    private JLabel label6;
    private JTextField bookTpye;
    private JScrollPane scrollPane1;
    private JTextArea bookDesc;
    private JButton clean;
    private JButton add;
    private JTextField search;
    private JButton loadingSearch;
    private JPanel picPanel;
    private JPanel picPanelFirst;
    private JLabel one;
    private JPanel picPanelTwo;
    private JLabel two;
    private JPanel picPanelThree;
    private JLabel three;
    private JPanel alterPanel;
    private JPanel bookAddPanel;
    private JLabel label2;
    private JPanel bookAlterPanel;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new mainPanel();
    }
}
