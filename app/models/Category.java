package models;

import com.avaje.ebean.Model;
import sun.rmi.runtime.Log;

import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by lxg on 04/04/2017.
 */
public class Category extends Model {

    @Id
    public Long category_id;

    public String name;

    public byte status;

    public Long sort_order;

    public Long parent_id;

    @OneToMany ProductToCategory product_to_category;

}
