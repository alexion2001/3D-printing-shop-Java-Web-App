package com.unibuc.shop.model;

import javax.persistence.*;


@Entity
@Table(name="compatibility")
public class Compatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long compatibilityId;

    @ManyToOne
    @JoinColumn(name = "filament_id")
    private Filament filamentId;


    @ManyToOne
    @JoinColumn(name = "printer_id")
    private Printer printerId;

    public Compatibility() {

    }
    public Compatibility(long compatibilityId, Filament filamentId, Printer printerId) {
        this.compatibilityId = compatibilityId;
        this.filamentId = filamentId;
        this.printerId = printerId;
    }
    public Compatibility(Filament filamentId, Printer printerId) {

        this.filamentId = filamentId;
        this.printerId = printerId;
    }

    public long getCompatibilityId() {
        return compatibilityId;
    }

    public void setCompatibilityId(long compatibilityId) {
        this.compatibilityId = compatibilityId;
    }

    public Filament getFilamentId() {
        return filamentId;
    }

    public void setFilamentId(Filament filamentId) {
        this.filamentId = filamentId;
    }

    public Printer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Printer printerId) {
        this.printerId = printerId;
    }
}