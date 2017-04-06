package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lxg on 04/04/2017.
 */
@Table(name="product_to_category")
@Entity
public class ProductToCategory extends Model{
    @Id
    //@Column(columnDefinition = "integer auto_increment not nul",name = "category_id")
    public int category_id;

    public Long product_id;


    @ManyToOne
    public List<Category> category;

    /**
     * Generic query helper for entity ProductToCategory with id Long
     */
    /*public static Finder<Long, ProductToCategory> find = new Finder<Long,ProductToCategory>(ProductToCategory.class);

    public List<ProductToCategory> list(){
        List<ProductToCategory> product_to_category = ProductToCategory.find
                .fetch("category")
                .where()
                .findList();
        return product_to_category;
    }*/


}
