package models;

import com.avaje.ebean.Model;
import sun.rmi.runtime.Log;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxg on 04/04/2017.
 */
@Table(name="category")
@Entity
public class Category extends Model {

    @Id
    public Long category_id;

    public String name;

    public byte status;

    public Long sort_order;

    public Long parent_id;

    @OneToMany
    public ProductToCategory product_to_category;

    /**
     * Generic query helper for entity Company with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Company c: Company.find.orderBy("name").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }
    public List<Category> list(){
        List<Category> category = Category.find.where()
                .eq("status",1)
                .orderBy("sort_order desc")
                .fetch("product_to_category")
                .findList();
        return category;
    }
}
