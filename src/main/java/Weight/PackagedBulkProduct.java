package Weight;

import Interfaces.Weightable;
import Primitive.Packaging;

import java.util.Objects;

public class PackagedBulkProduct implements Weightable {
    private BulkProduct product;
    private double productWeight; // масса весового товара в кг
    private Packaging packaging;

    public PackagedBulkProduct(BulkProduct product, double productWeight, Packaging packaging) {
        if (productWeight <= 0) {
            throw new IllegalArgumentException("Вес товара должен быть положительным");
        }
        this.product = product;
        this.productWeight = productWeight;
        this.packaging = packaging;
    }

    public BulkProduct getProduct() {
        return product;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    @Override
    public double getNetWeight() {
        return productWeight;
    }

    @Override
    public double getGrossWeight() {
        return productWeight + packaging.getWeight();
    }

    public String getName() {
        return product.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedBulkProduct that = (PackagedBulkProduct) o;
        return Double.compare(that.productWeight, productWeight) == 0 &&
                Objects.equals(product, that.product) &&
                Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, productWeight, packaging);
    }

    @Override
    public String toString() {
        return String.format("Упакованный весовой товар{товар=%s, вес товара=%.3f кг, %s}",
                product, productWeight, packaging);
    }
}