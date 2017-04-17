package models;

import javax.persistence.Embeddable;
import java.io.Serializable;

import static java.lang.Math.toIntExact;

/**
 * Created by lxg on 04/04/2017.
 */

@Embeddable
public class ProductToCategoryPK  implements Serializable {
    public Long product_id;

    public Long category_id;

    @Override
    public int hashCode() {
        return toIntExact(product_id) + toIntExact(category_id);
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
