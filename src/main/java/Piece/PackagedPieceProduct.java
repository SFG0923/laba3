package Piece;

import Interfaces.Weightable;
import Primitive.Packaging;

import java.util.Objects;

public class PackagedPieceProduct implements Weightable {
    private PieceProduct product;
    private int quantity;
    private Packaging packaging;

    public PackagedPieceProduct(PieceProduct product, int quantity, Packaging packaging) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество товара должно быть положительным");
        }
        this.product = product;
        this.quantity = quantity;
        this.packaging = packaging;
    }

    public PieceProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    @Override
    public double getNetWeight() {
        return product.getPieceWeight() * quantity;
    }

    @Override
    public double getGrossWeight() {
        return getNetWeight() + packaging.getWeight();
    }

    public String getName() {
        return product.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedPieceProduct that = (PackagedPieceProduct) o;
        return quantity == that.quantity &&
                Objects.equals(product, that.product) &&
                Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, packaging);
    }

    @Override
    public String toString() {
        return String.format("Упакованный штучный товар{товар=%s, количество=%d шт, %s}",
                product, quantity, packaging);
    }
}