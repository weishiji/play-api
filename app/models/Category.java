package models;

import com.avaje.ebean.FetchConfig;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.annotation.JsonIgnore;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import javafx.beans.DefaultProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.format.annotation.DateTimeFormat;
import play.data.format.Formats;
import play.data.validation.Constraints;
import utils.FormatSqlTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * Created by lxg on 04/04/2017.
 * http://stackoverflow.com/questions/15593978/error-reading-annotations-with-composite-key-in-ebean
 */
@Table(name="category")
@Entity
public class Category extends Model {
    private static final long serialVersionUID = 1L;
    private FormatSqlTime formatSqlTime = new FormatSqlTime();

    @Id
    @Column(name = "category_id")
    //@Constraints.Required
    @GeneratedValue
    private Long category_id;

    @Column(name = "name")
    @Constraints.Required
    private String name;

    private byte status;

    public int sort_order = 0;

    public int parent_id = 0;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    @WhenCreated
    private Date date_added;

    @Formats.DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
    @WhenModified
    private Date date_modified;

    //尼玛，关键是这两组属性都要写，浪费老子一周的事件
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    @OneToMany(mappedBy = "category")
    public List<ProductToCategory> product_to_category;


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

    public byte getStatus(){
        return status;
    }
    public void setStatus(byte aStatus){
        status = aStatus;
    }


    /**
     * Generic query helper for entity Category with id Long
     */
    public static Find<Long,Category> find = new Find<Long,Category>(){};

    public static List<Category> list(){
        //product_to_category = null;
        List<Category> category = Category.find
                .fetch("product_to_category",new FetchConfig().lazy())
                .fetch("product_to_category.product")
                .where()
                .eq("status",1)
                //.eq("category_id",1)
                .orderBy("sort_order asc")
                .findList();
        return category;
    }
    /**
     * Return a paged list of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Category> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            Category.find
                .fetch("product_to_category")
                .where()
                .eq("status",1)
                .findPagedList(page, pageSize);
    }

    public static Category getCategoryById(Long category_id){
        //product_to_category = null;
        Category category = Category.find.byId(category_id);

        return category;
    }
}

