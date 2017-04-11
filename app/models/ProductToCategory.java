package models;

import com.avaje.ebean.Model;
import org.springframework.context.annotation.Primary;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lxg on 04/04/2017.
 */

@Entity
@Table(name="product_to_category")
public class ProductToCategory extends Model{
    @Id
    @Column(name = "category_id")
    public Long category_id;

    @EmbeddedId
    public Long product_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product product;

/*
    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;
*/

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
