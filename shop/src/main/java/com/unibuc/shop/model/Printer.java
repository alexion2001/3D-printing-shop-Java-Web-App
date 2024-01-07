package com.unibuc.shop.model;

import javax.persistence.*;

@Entity
@Table(name="printer")
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long printerId;

    private String name;

    public Printer() {
    }

    public Printer(long printerId, String name) {
        this.printerId = printerId;
        this.name = name;
    }
    public Printer( String name) {
        this.name = name;
    }

    public long getPrinterId() {
        return printerId;
    }

    public void setPrinterId(long printerId) {
        this.printerId = printerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
