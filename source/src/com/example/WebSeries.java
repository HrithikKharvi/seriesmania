package com.example;

import java.io.Serializable;

public class WebSeries implements Serializable {

    private String name;
    private String rating;
    private String casts;
    private String language;
    private String description;

    private long serialVersionUID = 1L;


    public WebSeries(String name,String rating,String casts,String language,String description){
        this.name = name;
        this.rating = rating;
        this.casts = casts;
        this.language = language;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WebSeries{" +
                "name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", casts='" + casts + '\'' +
                ", language='" + language + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
