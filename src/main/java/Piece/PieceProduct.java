package Piece;

import java.util.Objects;
import Primitive.Product;

public class PieceProduct extends Product {
    private double pieceWeight;

    public PieceProduct(String name, String description, double pieceWeight) {
        super(name, description);
        if (pieceWeight <= 0) {
            throw new IllegalArgumentException("Вес штуки товара должен быть положительным");
        }
        this.pieceWeight = pieceWeight;
    }

    public double getPieceWeight() {
        return pieceWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PieceProduct that = (PieceProduct) o;
        return Double.compare(that.pieceWeight, pieceWeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pieceWeight);
    }

    @Override
    public String toString() {
        return String.format("Штучный товар{название='%s', описание='%s', вес штуки=%.3f кг}",
                getName(), getDescription(), pieceWeight);
    }
}