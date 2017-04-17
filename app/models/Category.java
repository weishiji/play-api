package models;

import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.PrivateOwned;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lxg on 04/04/2017.
 * http://stackoverflow.com/questions/15593978/error-reading-annotations-with-composite-key-in-ebean
 */
@Table(name="category")
@Entity
public class Category extends Model {

    @Id
    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "name")
    public String name;

    public byte status;

    public Long sort_order;

    public Long parent_id;

    public Long getCategory_id(){
        return  category_id;
    }
    public void setCategory_id(Long aCategory_id){
        category_id = aCategory_id;
    }

    @JoinColumn(name = "category_id")
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    public List<ProductToCategory> product_to_category;

    /**
     * Generic query helper for entity Category with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public List<Category> list(){

        List<Category> category = Category.find
                .fetch("product_to_category")
                //.fetch("product_to_category.product")
                .where()
                .eq("status",1)
                //.eq("category_id",1)
                .orderBy("sort_order asc")
                .findList();
        return category;
    }
}
