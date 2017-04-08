package models;

import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.PrivateOwned;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lxg on 04/04/2017.
 */
@Table(name="category")
@Entity
public class Category extends Model {

    @Id
    @Column(name = "category_id")
    public Long category_id;

    @Column(name = "name")
    public String name;

    public byte status;

    public Long sort_order;

    public Long parent_id;


    //定义join 的column name
    @JoinColumn(name="category_id",referencedColumnName = "category_id")
    //@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,mappedBy = "product")
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy="category")

    public List<ProductToCategory> product_to_category;


    /**
     * Generic query helper for entity Category with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public List<Category> list(){
        List<Category> category = Category.find
                .fetch("product_to_category")
                .fetch("product_to_category.product")
                .where()
                .eq("status",1)
                .eq("category_id",1)
                //.orderBy("sort_order asc")

                .findList();
        return category;
    }
}
