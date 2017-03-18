package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lxg on 18/03/2017.
 */
@Table(name="oc_customer")
@Entity
public class User extends Model {
    @Id
    public Long customer_id;

    @Constraints.Required
    public String email;
    
    @Constraints.Required
    public String password;



}
