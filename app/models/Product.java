package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by lxg on 17/03/2017.
 */
@Table(name="oc_product")
@Entity
public class Product extends Model {

    @Id
    public Long product_id;

    public String sku;

    public String model;

    public int quantity;
    /**
     * Generic query helper for entity Computer with id Long
     */
    //public static Finder<Long, Product> find = new Finder<Long,Product>(Product.class);
    public static Finder<Long, Product> find = new Finder<Long,Product>(Product.class);

    public Object list(){
        //List<Product> products = Product.find.setMaxRows(5).findList();
        List<Product> products = Product.find.where().findPagedList(10,10).getList();
        return products;
    }
}
