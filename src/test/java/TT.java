import dao.LoginDao;
import model.Book;
import model.User;
import view.mainPanel.mainPanel;

import javax.swing.*;

/**
 * @ClassName TT
 * @Description TODO
 * @Author
 * @Date 2021/2/3
 **/
public class TT {
    public static void main(String[] args) {
        Book book = new Book();
        book.setBookName("zhoujian");
        book.setBookPrice(1);
        book.setBookauthor("zhoujian");
        String name = book.getBookName();
        String bookauthor = book.getBookauthor();
        double bookPrice = book.getBookPrice();

        if (name.equals(name)) {
            if (bookauthor.equals(bookauthor)) {
                if (bookPrice==bookPrice) {
                    System.out.println("=================================================");
                                return;

                }
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");

    }




}
