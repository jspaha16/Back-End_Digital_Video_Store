package com.example.CJVAssignment2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class Movie {

    @Id
    private String id;
    private String Title;
    private String Year;
    private String Type;
    private String Poster;
    private int Rent;
    private int Buy;
    private  String Featured;
    private String Description;
    private String BigPoster;

    public Movie(String id, String Title, String Year, String Type, String Poster, int Rent, int Buy, String Featured, String Description, String BigPoster) {
        this.id = id;
        this.Title = Title;
        this.Year = Year;
        this.Type = Type;
        this.Poster = Poster;
        this.Rent = Rent;
        this.Buy = Buy;
        this.Featured = Featured;
        this.Description = Description;
        this.BigPoster = BigPoster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public int getRent() {
        return Rent;
    }

    public void setRent(int Rent) {
        this.Rent = Rent;
    }

    public int getBuy() {
        return Buy;
    }

    public void setBuy(int Buy) {
        this.Buy = Buy;
    }

    public String getFeatured() {
        return Featured;
    }

    public void setFeatured(String Featured) {
        this.Featured = Featured;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getBigPoster() {
        return BigPoster;
    }

    public void setBigPoster(String BigPoster) {
        this.BigPoster = BigPoster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Rent=" + Rent +
                ", Buy=" + Buy +
                ", Featured='" + Featured + '\'' +
                ", Description='" + Description + '\'' +
                ", BigPoster='" + BigPoster + '\'' +
                '}';
    }
}
