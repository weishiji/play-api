package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by lxg on 04/04/2017.
 */
@Table(name="product_to_category")
@Entity
public class ProductToCategory extends Model{
    @Id
    public Long product_id;

    @Constraints.Required
    public int category_id;

    /**
     * Generic query helper for entity ProductToCategory with id Long
     */
    public static Finder<Long, ProductToCategory> find = new Finder<Long,ProductToCategory>(ProductToCategory.class);

    public List<ProductToCategory> index(){
        List<ProductToCategory> product_to_category = ProductToCategory.find
                .where()
                .findList();
        return product_to_category;
    }


}
