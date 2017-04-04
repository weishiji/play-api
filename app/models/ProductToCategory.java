package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by lxg on 04/04/2017.
 */
public class ProductToCategory extends Model{
    @Id
    public Long product_id;

    @Constraints.Required
    public int category_id;

    @ManyToOne
    public Category category;



}
