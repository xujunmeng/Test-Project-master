package 第一个实例;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * 用户实体类
 * 验证规则如下：
 * userId最小为1
 * userName不为空
 * userPwd非空，长度在6-20之间
 * userAge的值在18-60之间
 * Created by james on 2017/5/23.
 */
public class User {

    @Min(1)
    private int userId;

    @NotNull(message = "用户名不能为空!")
    @Size(min = 1, message = "用户名不能为空!" )
    private String userName;

    @NotNull
    @Size(min = 6, max = 20, message = "密码长度必须在6-20之间!")
    private String userPwd;

    @Min(value = 18, message = "年龄最小值必须是18")
    @Max(value = 60, message = "年龄最大值必须是60")
    private int userAge;

    public User() {
    }

    public User(int userId, String userName, String userPwd, int userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User(1, "tanlan", "123", 10);
        Set<ConstraintViolation<User>> validate = validator.validate(user);
        for (ConstraintViolation<User> constraintViolation : validate) {
            System.out.println(constraintViolation.getMessage());
        }

    }

}
