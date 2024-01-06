package com.unibuc.shop.model;


import javax.persistence.*;

@Entity
@Table(name="filament")
public class Filament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long filamentId;

    private String type;

    private Integer piecesNumber;


    public Filament() {
    }

    public Filament(long filamentId) {
        this.filamentId = filamentId;
    }

    public Filament(String type, Integer piecesNumber) {
        this.type = type;
        this.piecesNumber = piecesNumber;
    }

    public Filament(long filamentId,String type, Integer piecesNumber) {
        this.filamentId = filamentId;
        this.type = type;
        this.piecesNumber = piecesNumber;
    }

    public long getId() {
        return filamentId;
    }

    public void setId(long id) {
        this.filamentId = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPiecesNumber() {
        return piecesNumber;
    }

    public void setPiecesNumber(Integer piecesNumber) {
        this.piecesNumber = piecesNumber;
    }


    @Override
    public String toString() {
        return "Filament: " +
                "type=" + type +
                ", piecesNumber=" + piecesNumber;
    }
}