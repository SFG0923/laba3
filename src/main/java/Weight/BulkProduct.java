package Weight;

import Primitive.Product;

public class BulkProduct extends Product {
    public BulkProduct(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        return String.format("Весовой товар{название='%s', описание='%s'}",
                getName(), getDescription());
    }
}