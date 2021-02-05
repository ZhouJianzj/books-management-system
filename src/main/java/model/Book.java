package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Book
 * @Description TODO
 * @Author
 * @Date 2021/2/5
 **/
@Getter
@Setter
public class Book {
    private int id;
    private String bookName;
    private String bookauthor;
    private String gender;
    private double bookPrice;
    private String bookType;
    private String bookDesc;
}
