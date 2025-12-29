package org.example;

import Interfaces.Filter;
import Interfaces.Weightable;
import Piece.PackagedPieceProduct;
import Weight.PackagedBulkProduct;

public class ProductService {

    public int countByFilter(ProductBatch batch, Filter filter) {
        if (batch == null || filter == null) {
            return 0;
        }

        int count = 0;
        String[] names = batch.getItemNames();

        for (String name : names) {
            if (filter.apply(name)) {
                count++;
            }
        }

        return count;
    }
    //дополнительные

    public static int countByFilterDeep(Weightable[] batch, Filter filter) {
        int count = 0;
        for (Weightable item : batch) {
            if (isProductInItem(item, filter)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isProductInItem(Weightable item, Filter filter) {
        if (item instanceof PackagedPieceProduct) {
            return filter.apply(((PackagedPieceProduct) item).getName());
        }
        if (item instanceof PackagedBulkProduct) {
            return filter.apply(((PackagedBulkProduct) item).getName());
        }
        if (item instanceof ProductSet) {
            for (Weightable subItem : ((ProductSet) item).getItems()) {
                if (isProductInItem(subItem, filter)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkAllWeighted(Weightable[] batch) {
        if (batch == null || batch.length == 0) return false;
        for (Weightable item : batch) {
            if (!(item instanceof PackagedBulkProduct)) {
                return false;
            }
        }
        return true;
    }

}
