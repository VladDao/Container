package com.plietnov.task1.entity;

import java.util.Objects;

public class ElectricalAppliance extends Product {

    private String type;

    public ElectricalAppliance() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        ElectricalAppliance electricalAppliances = (ElectricalAppliance) o;
        return getType().equals(electricalAppliances.getType());
    }

    @Override
    public String toString() {
        return "ElectricalAppliance{" +
                ", Id='" + getId() + '\'' +
                ", Type='" + type + '\'' +
                '}';
    }
}
