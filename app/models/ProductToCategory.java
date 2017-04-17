package models;

import com.avaje.ebean.Model;
//import utils.ProductToCategoryPK;

import javax.persistence.*;
import java.io.Serializable;

import static java.lang.Math.toIntExact;


@Entity
@Table(name="product_to_category")
public class ProductToCategory extends Model{
    @Id
    @Column(name = "category_id")
    public Long category_id;

    public Long product_id;

    @EmbeddedId
    private ProductToCategoryPK pk = new ProductToCategoryPK();

    ProductToCategory(){
        pk = new ProductToCategoryPK();
    }

    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    private Product product;

    public Category getCategory(){
        return category;
    }

    public Product getProduct(){
        return  product;
    }

    public void setCategory(Category aCategory){
        category = aCategory;
        pk.category_id = aCategory.category_id;
    }

    public void setProduct(Product aProduct){
        product = aProduct;
        pk.product_id = aProduct.product_id;
    }

/*

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product product;
*/

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

