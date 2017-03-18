package models;

import com.avaje.ebean.Model;
import org.jetbrains.annotations.NotNull;
import play.Logger;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lxg on 18/03/2017.
 */
@Table(name="oc_customer")
@Entity
public class User extends Model {
    @Id
    public Long customer_id;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    @Constraints.MaxLength(20)
    @Constraints.MinLength(4)
    public String password;


    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    /**
     * 增加错误类型
     * */
    public List<ValidationError> validate() {
        User user = new User();
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (user.getUserByEmail(email) != null) {
            errors.add(new ValidationError("email", "This e-mail is already registered."));
        }
        return errors.isEmpty() ? null : errors;
    }
    /**
    * 验证Email是否被占用
    * */
    public Object getUserByEmail(String email){
        return find.where().eq("email",email).findUnique();
    }
}
