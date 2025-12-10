package Primitive;

import java.util.Objects;

public class Packaging {
    private String name;
    private double weight; // собственный вес упаковки в кг

    public Packaging(String name, double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Вес упаковки не может быть отрицательным");
        }
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packaging packaging = (Packaging) o;
        return Double.compare(packaging.weight, weight) == 0 &&
                Objects.equals(name, packaging.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return String.format("Упаковка{название='%s', вес=%.3f кг}", name, weight);
    }
}