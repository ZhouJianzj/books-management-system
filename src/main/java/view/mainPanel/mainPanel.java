/*
 * Created by JFormDesigner on Wed Feb 03 11:20:51 CST 2021
 */

package view.mainPanel;

import java.beans.*;
import javax.lang.model.element.VariableElement;
import javax.swing.table.*;

import dao.alterBookTypeManager.*;
import dao.booAddPanel.addBookDoAdd;
import dao.booAddPanel.getJComboxData;
import dao.bookTypeManager.LoadingSearch;
import dao.bookTypeManager.bookTypeAdd;
import jdk.nashorn.internal.objects.NativeUint8Array;
import jdk.nashorn.internal.scripts.JO;
import model.Book;
import model.BookType;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        getTableData();

    }

    /**
     * 图书添加事件
     * @param e
     */
    private void bookAddMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        cut("card4");
        jComboBoxData();
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

    /**
     * 轮播图的实现
     * @param e
     */
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

    /**
     * 安全退出事件
     * @param e
     */
    private void exitActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 0：代表的是确认 1:代表的是否 2：代表的是取消
        int i = JOptionPane.showConfirmDialog(null, "是否确认退出！");
        if (i == 0){
            mianForm.dispose();
        }
    }

    /**
     * 获取所有的图书类别展示到表格
     */
    public void getTableData(){
        showTableData data = new showTableData();
        ResultSet resultSet = data.showTableData(alterPanelTable);
        if (resultSet == null){
            JOptionPane.showMessageDialog(alterPanel,"图书还没有类别");
        }
    }

    /**
     * 修改图书类别panel中的搜索按钮事件
     * @param e
     */
    private void alterSearchButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        alterSearch alterSearch = new alterSearch();
        String text = alterSearchText.getText();
        if (text == null || "".equals(text)) {
            getTableData();
            JOptionPane.showMessageDialog(alterPanel,"请输入需要查询的图书类别名称！");
        }else{
            alterSearch.doSearch(alterSearchText,alterPanelTable);
        }
    }

    /**
     * alterPanel中delete按钮事件
     * @param e
     */
    private void deleteTypeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here

        if (alterPanelID.getText() == null || "".equals(alterPanelID.getText())) {
            JOptionPane.showMessageDialog(alterPanel,"没有选择要删除的图书类别！");
        }else if (JOptionPane.showConfirmDialog(alterPanel, "是否确认删除！") == 0) {
            if(new CheckExist().checkDeleteData(alterPanelID)){
                JOptionPane.showMessageDialog(alterPanel,"该图书类别下有图书，不可以删除！");
            }else{
                deleteType deleteType = new deleteType();
                int i = deleteType.deleteData(alterPanelID);
                if (i == 1) {
                    getTableData();
                    alterPanelID.setText("");
                    图书类别名称.setText("");
                    描述.setText("");
                    JOptionPane.showMessageDialog(alterPanel, "删除成功！");
                }
            }
        }
    }

    /**
     * alterPanel中修改按钮事件
     * @param e
     */
    private void alterTypeAddButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (alterPanelID.getText() == null || "".equals(alterPanelID.getText())){
            JOptionPane.showMessageDialog(alterPanel,"没有选择需要修改的图书类别！");
        }else if (alterPanelID.getText().equals(alterPanelTable.getValueAt(alterPanelTable.getSelectedRow(),0))
                    && 图书类别名称.getText().equals(alterPanelTable.getValueAt(alterPanelTable.getSelectedRow(),1))
                    && 描述.getText().equals(alterPanelTable.getValueAt(alterPanelTable.getSelectedRow(),2))){
            JOptionPane.showMessageDialog(alterPanel,"你还没有做任何的修改！");

        } else{
            alterType alterType = new alterType();
            alterType.alterData(alterPanelID,图书类别名称,描述);
            getTableData();
            JOptionPane.showMessageDialog(alterPanel,"修改成功！");
        }
    }

    /**
     * 选中表格传递数据事件
     * @param e
     */
    private void alterPanelTableMousePressed(MouseEvent e) {
        // TODO add your code here
        int selectedRow = alterPanelTable.getSelectedRow();
        alterPanelID.setText((String)alterPanelTable.getValueAt(selectedRow,0));
        图书类别名称.setText((String)alterPanelTable.getValueAt(selectedRow,1));
        描述.setText((String)alterPanelTable.getValueAt(selectedRow,2));
    }

    /**
     * 设置图书类别的下拉框数据
     */
    public void jComboBoxData(){
        getJComboxData getJComboxData = new getJComboxData();
        getJComboxData.getData(addBookType);
    }
    /**
     * bookAddPanel面板中的添加按钮事件
     * @param e
     */
    private void addBookAddActionPerformed(ActionEvent e) {
        // TODO add your code here
        //获取到性别选中的数据转换成1或者0
        //还需要设置JCombox的选择栏目的内容

        if (addBookName.getText() == null || addBookAuthor.getText() == null || addBookPrice.getText() ==null ||
        addBookDesc.getText() == null ||"".equals(addBookName.getText())||"".equals(addBookAuthor.getText())||
                "".equals(addBookPrice.getText())||"".equals( addBookDesc.getText())){
            JOptionPane.showMessageDialog(bookAddPanel,"请务必填写相关书籍信息！");
        }else{
            String sm = null;
            if(addSex.getText() != null){
                sm = "0";
            }
            if (addMan.getText() !=null){
                sm = "1";
            }
            addBookDoAdd add = new addBookDoAdd();
            Book book = new Book();
            book.setBookName(addBookName.getText());
            book.setBookauthor(addBookAuthor.getText());
            book.setGender(sm);
            book.setBookPrice(Double.parseDouble(addBookPrice.getText()));
            book.setBookType(addBookType.getSelectedItem().toString());
            book.setBookDesc(addBookDesc.getText());
            boolean b = add.doAddCheck(book);
            if (b != true){
                System.out.println(b);
                JOptionPane.showMessageDialog(bookAddPanel,"有重复的书籍存在了！");
            }else{
                boolean b1 = add.doAdd(book);
                if (b1){
                    JOptionPane.showMessageDialog(bookAddPanel,"添加成功");
                }
            }
        }


    }

    /**
     * bookAddpanel中的重置按钮
     * @param e
     */
    private void addBookResertActionPerformed(ActionEvent e) {
        // TODO add your code here
        addBookName.setText("");
        addBookAuthor.setText("");
        addBookPrice.setText("");
        addBookDesc.setText("");
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
        label3 = new JLabel();
        alterSearchText = new JTextField();
        alterSearchButton = new JButton();
        scrollPane2 = new JScrollPane();
        alterPanelTable = new JTable();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        textLablell = new JLabel();
        scrollPane3 = new JScrollPane();
        描述 = new JTextArea();
        alterTypeButton = new JButton();
        deleteTypeButton = new JButton();
        图书类别名称 = new JTextField();
        alterPanelID = new JTextField();
        bookAddPanel = new JPanel();
        label2 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        scrollPane4 = new JScrollPane();
        addBookDesc = new JTextArea();
        addBookAdd = new JButton();
        addBookResert = new JButton();
        addBookName = new JTextField();
        addBookAuthor = new JTextField();
        addBookPrice = new JTextField();
        addBookType = new JComboBox();
        addSex = new JRadioButton();
        addMan = new JRadioButton();
        bookAlterPanel = new JPanel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        bookName = new JTextField();
        bookauthor = new JTextField();
        bookAlterSearch = new JButton();
        comboBox2 = new JComboBox();
        scrollPane6 = new JScrollPane();
        table1 = new JTable();
        label27 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        label30 = new JLabel();
        label31 = new JLabel();
        label32 = new JLabel();
        label33 = new JLabel();
        label34 = new JLabel();
        scrollPane7 = new JScrollPane();
        textArea2 = new JTextArea();
        button5 = new JButton();
        button6 = new JButton();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        textField11 = new JTextField();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        comboBox3 = new JComboBox();

        //======== mianForm ========
        {
            mianForm.setVisible(true);
            mianForm.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
                welcome.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,welcome. getBorder( )) ); welcome. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("\u0062or\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

                //---- label5 ----
                label5.setText(bundle.getString("label5.text"));
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                label5.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));

                GroupLayout welcomeLayout = new GroupLayout(welcome);
                welcome.setLayout(welcomeLayout);
                welcomeLayout.setHorizontalGroup(
                    welcomeLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, welcomeLayout.createSequentialGroup()
                            .addContainerGap(217, Short.MAX_VALUE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
                            .addGap(194, 194, 194))
                );
                welcomeLayout.setVerticalGroup(
                    welcomeLayout.createParallelGroup()
                        .addGroup(welcomeLayout.createSequentialGroup()
                            .addGap(224, 224, 224)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(350, Short.MAX_VALUE))
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

                //---- label3 ----
                label3.setText(bundle.getString("label3.text"));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- alterSearchButton ----
                alterSearchButton.setText("\u641c\u7d22");
                alterSearchButton.setIcon(new ImageIcon(getClass().getResource("/pic/login/sousuo.png")));
                alterSearchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        alterSearchButtonActionPerformed(e);
                    }
                });

                //======== scrollPane2 ========
                {

                    //---- alterPanelTable ----
                    alterPanelTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "\u7f16\u53f7", "\u56fe\u4e66\u7c7b\u522b\u540d\u79f0", "\u56fe\u4e66\u7c7b\u522b\u63cf\u8ff0"
                        }
                    ) {
                        boolean[] columnEditable = new boolean[] {
                            false, false, false
                        };
                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return columnEditable[columnIndex];
                        }
                    });
                    alterPanelTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            alterPanelTableMousePressed(e);
                        }
                    });
                    scrollPane2.setViewportView(alterPanelTable);
                }

                //---- label7 ----
                label7.setText(bundle.getString("label7.text"));
                label7.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));

                //---- label8 ----
                label8.setText(bundle.getString("label8.text"));
                label8.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

                //---- label9 ----
                label9.setText(bundle.getString("label9.text"));
                label9.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

                //---- textLablell ----
                textLablell.setText(bundle.getString("textLablell.text"));
                textLablell.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(描述);
                }

                //---- alterTypeButton ----
                alterTypeButton.setText(bundle.getString("alterTypeButton.text"));
                alterTypeButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                alterTypeButton.setIcon(new ImageIcon(getClass().getResource("/pic/login/xiugai.png")));
                alterTypeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        alterTypeAddButtonActionPerformed(e);
                    }
                });

                //---- deleteTypeButton ----
                deleteTypeButton.setText(bundle.getString("deleteTypeButton.text"));
                deleteTypeButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                deleteTypeButton.setIcon(new ImageIcon(getClass().getResource("/pic/login/chushaixuanxiang.png")));
                deleteTypeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteTypeButtonActionPerformed(e);
                    }
                });

                //---- alterPanelID ----
                alterPanelID.setHorizontalAlignment(SwingConstants.CENTER);
                alterPanelID.setEditable(false);

                GroupLayout alterPanelLayout = new GroupLayout(alterPanel);
                alterPanel.setLayout(alterPanelLayout);
                alterPanelLayout.setHorizontalGroup(
                    alterPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, alterPanelLayout.createSequentialGroup()
                            .addGroup(alterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(alterPanelLayout.createSequentialGroup()
                                    .addGap(0, 175, Short.MAX_VALUE)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE))
                                .addGroup(alterPanelLayout.createSequentialGroup()
                                    .addGap(149, 149, 149)
                                    .addComponent(deleteTypeButton)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                                    .addComponent(alterTypeButton)))
                            .addGap(172, 172, 172))
                        .addGroup(alterPanelLayout.createSequentialGroup()
                            .addGroup(alterPanelLayout.createParallelGroup()
                                .addGroup(alterPanelLayout.createSequentialGroup()
                                    .addGap(212, 212, 212)
                                    .addGroup(alterPanelLayout.createParallelGroup()
                                        .addGroup(alterPanelLayout.createSequentialGroup()
                                            .addComponent(label8)
                                            .addGap(56, 56, 56)
                                            .addComponent(alterPanelID, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                            .addGap(116, 116, 116)
                                            .addComponent(textLablell)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(图书类别名称, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(alterPanelLayout.createSequentialGroup()
                                            .addComponent(label9)
                                            .addGap(18, 18, 18)
                                            .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(alterPanelLayout.createSequentialGroup()
                                    .addGap(220, 220, 220)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(alterSearchText, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)
                                    .addComponent(alterSearchButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
                                .addGroup(alterPanelLayout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(186, Short.MAX_VALUE))
                );
                alterPanelLayout.setVerticalGroup(
                    alterPanelLayout.createParallelGroup()
                        .addGroup(alterPanelLayout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addGroup(alterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(alterSearchButton)
                                .addComponent(alterSearchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addGap(43, 43, 43)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(alterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label8)
                                .addComponent(图书类别名称, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textLablell)
                                .addComponent(alterPanelID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31)
                            .addGroup(alterPanelLayout.createParallelGroup()
                                .addComponent(label9)
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
                            .addGap(51, 51, 51)
                            .addGroup(alterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(alterTypeButton)
                                .addComponent(deleteTypeButton))
                            .addContainerGap(75, Short.MAX_VALUE))
                );
            }
            mianFormContentPane.add(alterPanel, "card3");

            //======== bookAddPanel ========
            {

                //---- label2 ----
                label2.setText(bundle.getString("label2.text"));
                label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label10 ----
                label10.setText(bundle.getString("label10.text"));
                label10.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label11 ----
                label11.setText(bundle.getString("label11.text"));
                label11.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label12 ----
                label12.setText(bundle.getString("label12.text"));
                label12.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label13 ----
                label13.setText(bundle.getString("label13.text"));
                label13.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label14 ----
                label14.setText(bundle.getString("label14.text"));
                label14.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(addBookDesc);
                }

                //---- addBookAdd ----
                addBookAdd.setText(bundle.getString("addBookAdd.text"));
                addBookAdd.setIcon(new ImageIcon(getClass().getResource("/pic/login/jiashang.png")));
                addBookAdd.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                addBookAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addBookAddActionPerformed(e);
                    }
                });

                //---- addBookResert ----
                addBookResert.setText(bundle.getString("addBookResert.text"));
                addBookResert.setIcon(new ImageIcon(getClass().getResource("/pic/login/miaoshu.png")));
                addBookResert.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
                addBookResert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addBookResertActionPerformed(e);
                    }
                });

                //---- addSex ----
                addSex.setText(bundle.getString("addSex.text"));

                //---- addMan ----
                addMan.setText(bundle.getString("addMan.text"));
                addMan.setSelected(true);

                GroupLayout bookAddPanelLayout = new GroupLayout(bookAddPanel);
                bookAddPanel.setLayout(bookAddPanelLayout);
                bookAddPanelLayout.setHorizontalGroup(
                    bookAddPanelLayout.createParallelGroup()
                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addGroup(bookAddPanelLayout.createParallelGroup()
                                .addGroup(bookAddPanelLayout.createSequentialGroup()
                                    .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label2)
                                        .addComponent(label10)
                                        .addComponent(label13)
                                        .addComponent(label14))
                                    .addGap(18, 18, 18)
                                    .addGroup(bookAddPanelLayout.createParallelGroup()
                                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                                            .addComponent(addSex)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(addMan)
                                            .addContainerGap(636, Short.MAX_VALUE))
                                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addGroup(bookAddPanelLayout.createParallelGroup()
                                                .addGroup(bookAddPanelLayout.createSequentialGroup()
                                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap(292, Short.MAX_VALUE))
                                                .addGroup(bookAddPanelLayout.createSequentialGroup()
                                                    .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(addBookType, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addBookName, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                                    .addGroup(bookAddPanelLayout.createParallelGroup()
                                                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                                                            .addComponent(label11)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(addBookAuthor, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                                                            .addComponent(label12)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(addBookPrice, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
                                                    .addGap(199, 199, 199))))))
                                .addGroup(bookAddPanelLayout.createSequentialGroup()
                                    .addComponent(addBookResert)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
                                    .addComponent(addBookAdd)
                                    .addGap(219, 219, 219))))
                );
                bookAddPanelLayout.setVerticalGroup(
                    bookAddPanelLayout.createParallelGroup()
                        .addGroup(bookAddPanelLayout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(label11)
                                .addComponent(addBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(addBookAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(55, 55, 55)
                            .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label10)
                                .addComponent(addSex)
                                .addComponent(label12)
                                .addComponent(addMan)
                                .addComponent(addBookPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(46, 46, 46)
                            .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label13)
                                .addComponent(addBookType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(75, 75, 75)
                            .addGroup(bookAddPanelLayout.createParallelGroup()
                                .addComponent(label14)
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                            .addGroup(bookAddPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addBookResert)
                                .addComponent(addBookAdd))
                            .addGap(98, 98, 98))
                );
            }
            mianFormContentPane.add(bookAddPanel, "card4");

            //======== bookAlterPanel ========
            {
                bookAlterPanel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label15 ----
                label15.setText(bundle.getString("label15.text"));
                label15.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label16 ----
                label16.setText(bundle.getString("label16.text"));

                //---- label17 ----
                label17.setText(bundle.getString("label17.text"));

                //---- label18 ----
                label18.setText(bundle.getString("label18.text"));

                //---- bookAlterSearch ----
                bookAlterSearch.setText(bundle.getString("bookAlterSearch.text"));
                bookAlterSearch.setIcon(new ImageIcon(getClass().getResource("/pic/login/sousuo.png")));

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(table1);
                }

                //---- label27 ----
                label27.setText(bundle.getString("label27.text"));
                label27.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

                //---- label28 ----
                label28.setText(bundle.getString("label28.text"));

                //---- label29 ----
                label29.setText(bundle.getString("label29.text"));

                //---- label30 ----
                label30.setText(bundle.getString("label30.text"));

                //---- label31 ----
                label31.setText(bundle.getString("label31.text"));

                //---- label32 ----
                label32.setText(bundle.getString("label32.text"));

                //---- label33 ----
                label33.setText(bundle.getString("label33.text"));

                //---- label34 ----
                label34.setText(bundle.getString("label34.text"));

                //======== scrollPane7 ========
                {
                    scrollPane7.setViewportView(textArea2);
                }

                //---- button5 ----
                button5.setText(bundle.getString("button5.text"));
                button5.setIcon(new ImageIcon(getClass().getResource("/pic/login/chushaixuanxiang.png")));

                //---- button6 ----
                button6.setText(bundle.getString("button6.text"));
                button6.setIcon(new ImageIcon(getClass().getResource("/pic/login/xiugai.png")));

                //---- radioButton3 ----
                radioButton3.setText(bundle.getString("radioButton3.text"));

                //---- radioButton4 ----
                radioButton4.setText(bundle.getString("radioButton4.text"));

                GroupLayout bookAlterPanelLayout = new GroupLayout(bookAlterPanel);
                bookAlterPanel.setLayout(bookAlterPanelLayout);
                bookAlterPanelLayout.setHorizontalGroup(
                    bookAlterPanelLayout.createParallelGroup()
                        .addGroup(bookAlterPanelLayout.createSequentialGroup()
                            .addGroup(bookAlterPanelLayout.createParallelGroup()
                                .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addGroup(bookAlterPanelLayout.createParallelGroup()
                                        .addComponent(label15)
                                        .addComponent(label27)))
                                .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(GroupLayout.Alignment.LEADING, bookAlterPanelLayout.createSequentialGroup()
                                        .addGap(106, 106, 106)
                                        .addGroup(bookAlterPanelLayout.createParallelGroup()
                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addGroup(bookAlterPanelLayout.createParallelGroup()
                                                        .addComponent(label28)
                                                        .addComponent(label29))
                                                    .addComponent(label34))
                                                .addGap(18, 18, 18)
                                                .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                        .addGroup(bookAlterPanelLayout.createParallelGroup()
                                                            .addComponent(textField8, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(textField9, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                                        .addGap(37, 37, 37)
                                                        .addGroup(bookAlterPanelLayout.createParallelGroup()
                                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                                .addComponent(label31)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(textField10, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                                .addComponent(label30)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(textField11, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(29, 29, 29)
                                                        .addGroup(bookAlterPanelLayout.createParallelGroup()
                                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                                .addComponent(label32)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(radioButton3)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(radioButton4))
                                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                                .addComponent(label33)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))))
                                                    .addComponent(scrollPane7)))
                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                .addComponent(button5)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 626, Short.MAX_VALUE)
                                                .addComponent(button6))))
                                    .addGroup(GroupLayout.Alignment.LEADING, bookAlterPanelLayout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(scrollPane6)
                                            .addGroup(bookAlterPanelLayout.createSequentialGroup()
                                                .addComponent(label16)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bookName, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(label17)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bookauthor, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(label18)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bookAlterSearch))))))
                            .addGap(66, 66, 66))
                );
                bookAlterPanelLayout.setVerticalGroup(
                    bookAlterPanelLayout.createParallelGroup()
                        .addGroup(bookAlterPanelLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addComponent(label15)
                            .addGap(35, 35, 35)
                            .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label17)
                                .addComponent(bookauthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(bookAlterSearch)
                                .addComponent(label16)
                                .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label18)
                                .addComponent(bookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                            .addGap(61, 61, 61)
                            .addComponent(label27)
                            .addGap(28, 28, 28)
                            .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label28)
                                .addComponent(label30)
                                .addComponent(label32)
                                .addComponent(textField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(radioButton3)
                                .addComponent(radioButton4))
                            .addGap(31, 31, 31)
                            .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label29)
                                .addComponent(label31)
                                .addComponent(label33)
                                .addComponent(textField9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                            .addGroup(bookAlterPanelLayout.createParallelGroup()
                                .addComponent(label34)
                                .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                            .addGap(49, 49, 49)
                            .addGroup(bookAlterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button5)
                                .addComponent(button6))
                            .addGap(28, 28, 28))
                );
            }
            mianFormContentPane.add(bookAlterPanel, "card5");
            mianForm.setSize(1020, 800);
            mianForm.setLocationRelativeTo(mianForm.getOwner());
        }

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(addSex);
        buttonGroup1.add(addMan);
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
    private JLabel label3;
    private JTextField alterSearchText;
    private JButton alterSearchButton;
    private JScrollPane scrollPane2;
    private JTable alterPanelTable;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel textLablell;
    private JScrollPane scrollPane3;
    private JTextArea 描述;
    private JButton alterTypeButton;
    private JButton deleteTypeButton;
    private JTextField 图书类别名称;
    private JTextField alterPanelID;
    private JPanel bookAddPanel;
    private JLabel label2;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JScrollPane scrollPane4;
    private JTextArea addBookDesc;
    private JButton addBookAdd;
    private JButton addBookResert;
    private JTextField addBookName;
    private JTextField addBookAuthor;
    private JTextField addBookPrice;
    private JComboBox addBookType;
    private JRadioButton addSex;
    private JRadioButton addMan;
    private JPanel bookAlterPanel;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JTextField bookName;
    private JTextField bookauthor;
    private JButton bookAlterSearch;
    private JComboBox comboBox2;
    private JScrollPane scrollPane6;
    private JTable table1;
    private JLabel label27;
    private JLabel label28;
    private JLabel label29;
    private JLabel label30;
    private JLabel label31;
    private JLabel label32;
    private JLabel label33;
    private JLabel label34;
    private JScrollPane scrollPane7;
    private JTextArea textArea2;
    private JButton button5;
    private JButton button6;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JComboBox comboBox3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new mainPanel();
    }
}
