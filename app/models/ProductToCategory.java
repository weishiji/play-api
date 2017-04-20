package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import utils.ProductToCategoryPK;

import javax.persistence.*;
import java.io.Serializable;

import static java.lang.Math.toIntExact;


@Entity
@Table(name="product_to_category")
public class ProductToCategory extends Model{

    @EmbeddedId
    protected ProductToCategoryPK pk;

    ProductToCategory(){
        pk = new ProductToCategoryPK();
    }

    @ManyToOne
    @JsonIgnore //要写忽略这个输出，否则会出现递归错误，尼玛，这个也坑我好久
    @JoinColumn(name = "category_id",referencedColumnName = "category_id",insertable = false,updatable = false)
    private Category category;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id",insertable = false,updatable = false)
    private Product product;


    public Category getCategory(){
        return category;
    }
    public void setCategory(Category aCategory){
        category = aCategory;
        pk.category_id = aCategory.getCategory_id();
    }

    public Product getProduct(){
        return  product;
    }
    public void setProduct(Product aProduct){
        product = aProduct;
        pk.product_id = aProduct.getProduct_id();
    }


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

