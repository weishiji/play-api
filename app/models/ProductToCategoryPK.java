package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

import static java.lang.Math.toIntExact;

/**
 * Created by lxg on 04/04/2017.
 */

@Embeddable
public class ProductToCategoryPK  implements Serializable {

    @Column(name = "product_id")
    public Long product_id;

    @Column(name = "category_id")
    public Long category_id;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.product_id != null ? this.product_id.hashCode() : 0);
        hash = 89 * hash + (this.category_id != null ? this.category_id.hashCode() : 0);
        return hash;

    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        ProductToCategoryPK pk = (ProductToCategoryPK)obj;
        if(pk == null)
            return false;
        if(pk.category_id.equals(category_id) && pk.product_id.equals(product_id)) {
            return true;
        }
        return false;
    }
}
