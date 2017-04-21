package models;

import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Model;
import javafx.beans.DefaultProperty;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lxg on 04/04/2017.
 * http://stackoverflow.com/questions/15593978/error-reading-annotations-with-composite-key-in-ebean
 */
@Table(name="category")
@Entity
public class Category extends Model {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_id")
    @Constraints.Required
    private Long category_id;

    @Column(name = "name")
    @Constraints.Required
    private String name;

    public byte status = 1;

    public int sort_order = 0;

    public int parent_id = 0;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date date_added = new Date();

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date date_modified = new Date();

    public Long getCategory_id(){
        return  category_id;
    }
    public void setCategory_id(Long aCategory_id){
        category_id = aCategory_id;
    }

    public String getName(){
        return  name;
    }
    public void setName(String aName){
        name = aName;
    }

    //尼玛，关键是这两组属性都要写，浪费老子一周的事件
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    @OneToMany(mappedBy = "category")
    public List<ProductToCategory> product_to_category;

    /**
     * Generic query helper for entity Category with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public List<Category> list(){

        List<Category> category = Category.find
                .fetch("product_to_category",new FetchConfig().lazy())
                //.fetch("product_to_category.product")
                .where()
                .eq("status",1)
                //.eq("category_id",1)
                .orderBy("sort_order asc")
                .findList();
        return category;
    }

}
