package models;

import com.avaje.ebean.Model;
import sun.rmi.runtime.Log;

import javax.persistence.*;
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
    //@Column(name = "category_id")
    public Long category_id;

    public String name;

    public byte status;

    public Long sort_order;

    public Long parent_id;

    @OneToMany(cascade = CascadeType.ALL)
    //定义join 的column name
    @JoinColumn(name="category_id")
    public List<ProductToCategory> product_to_category;

    /**
     * Generic query helper for entity Category with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public List<Category> list(){
        List<Category> category = Category.find

                .where()
                .eq("status",1)
                .orderBy("sort_order desc")
                .fetch("product_to_category")
                .findList();
        return category;
    }
}
