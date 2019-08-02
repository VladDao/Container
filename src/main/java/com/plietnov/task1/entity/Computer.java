package com.plietnov.task1.entity;

import java.util.Objects;

public class Computer extends ElectricalAppliance {

    private String classification;

    public Computer() {
    }

    public Computer(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getClassification());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        Computer computer = (Computer) o;
        return getClassification().equals(computer.getClassification());
    }

    @Override
    public String toString() {
        return "Computer{" +
                ", Id='" + getId() + '\'' +
                ", Name='" + getNameOfProduct() + '\'' +
                ", Type='" + getType() + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
