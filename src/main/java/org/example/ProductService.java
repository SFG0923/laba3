package org.example;

import Interfaces.Filter;

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
}