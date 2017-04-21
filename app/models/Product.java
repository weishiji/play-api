package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;
import utils.FormatSqlTime;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lxg on 17/03/2017.
 */
@Table(name="product")
@Entity
public class Product extends Model {

    @Id
    private Long product_id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String image;

    public byte status = 1;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date date_available;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date date_added;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date date_modified;

    public int quantity;

    @OneToOne(mappedBy = "product")
    private ProductToCategory product_to_category;

    public Long getProduct_id(){
        return product_id;
    }

    private FormatSqlTime formatSqlTime = new FormatSqlTime();

    public String getDateAvailable(){
        return formatSqlTime.format(date_available);
    }
    public String getDateModified(){
        return formatSqlTime.format(date_modified);
    }
    public String getDateAdded()
    {
        return formatSqlTime.format(date_added);
    }    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long, Product> find = new Finder<Long,Product>(Product.class);

    public Object list(){
        //List<Product> products = Product.find.setMaxRows(5).findList();
        List<Product> products = Product.find.where().findPagedList(0,10).getList();
        return products;
    }
}
