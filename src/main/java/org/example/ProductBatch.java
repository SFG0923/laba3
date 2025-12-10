package org.example;

import Interfaces.Weightable;
import Piece.PackagedPieceProduct;
import Weight.PackagedBulkProduct;

import java.util.Objects;

public class ProductBatch {
    private String description;
    private Weightable[] items; // массив упакованных товаров

    public ProductBatch(String description, Weightable[] items) {
        if (items == null || items.length == 0) {
            throw new IllegalArgumentException("Партия должна содержать товары");
        }
        this.description = description;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public Weightable[] getItems() {
        return items;
    }

    public double getTotalWeight() {
        double total = 0;
        for (Weightable item : items) {
            total += item.getGrossWeight();
        }
        return total;
    }

    public String[] getItemNames() {
        String[] names = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof PackagedBulkProduct) {
                names[i] = ((PackagedBulkProduct) items[i]).getName();
            } else if (items[i] instanceof PackagedPieceProduct) {
                names[i] = ((PackagedPieceProduct) items[i]).getName();
            }
        }
        return names;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Партия товаров{описание='%s', товары:\n", description));
        for (Weightable item : items) {
            sb.append("  ").append(item.toString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}