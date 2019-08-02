package com.plietnov.task1.entity;

import java.util.Objects;

public class LeapTop extends Computer {

    private String description;

    public LeapTop() {
    }

    public LeapTop(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())) {
            return false;
        }
        LeapTop leapTop = (LeapTop) o;
        return getDescription().equals(leapTop.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription());
    }

    @Override
    public String toString() {
        return "LeapTop{" +
                ", Id='" + getId() + '\'' +
                ", Type='" + getType() + '\'' +
                ", Class='" + getClassification() + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
