package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * 增加错误类型
     * */
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (User.byEmail(email) != null) {
            errors.add(new ValidationError("email", "This e-mail is already registered."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public static String byEmail(String email){

        return "";
    }
}
