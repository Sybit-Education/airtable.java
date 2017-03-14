package com.sybit.airtable;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class Location {
    private String id;
    private String name;
    private String beschreibung;
    private String foto;
    @SerializedName("GEO Koordinaten")
    private String geoKoordinaten;
    private String fragen;
    private String medien;
    private String code;
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGeoKoordinaten() {
        return geoKoordinaten;
    }

    public void setGeoKoordinaten(String geoKoordinaten) {
        this.geoKoordinaten = geoKoordinaten;
    }

    public String getFragen() {
        return fragen;
    }

    public void setFragen(String fragen) {
        this.fragen = fragen;
    }

    public String getMedien() {
        return medien;
    }

    public void setMedien(String medien) {
        this.medien = medien;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
