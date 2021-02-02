package model;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName user
 * @Description TODO
 * @Author
 * @Date 2021/2/2
 **/

/**
    用户实体
 */
@Setter
@Getter
public class User {
    private int id;
    private  String userName;
    private  String password;
}
