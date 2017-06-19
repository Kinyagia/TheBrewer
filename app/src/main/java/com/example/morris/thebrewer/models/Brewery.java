package com.example.morris.thebrewer.models;

import org.parceler.Parcel;

/**
 * Created by morris on 6/9/17.
 */

@Parcel
public class Brewery {
    private  String name;
    private String winery;
    private String varietal;
    private String price;
    private String vintage;
    private String type;
    private String link;
    private String image;

public Brewery() {}


public Brewery(String name, String winery, String varietal, String price, String vintage, String type, String link, String image) {
    this.name = name;
    this.winery = winery;
    this.varietal = varietal;
    this.price = price;
    this.vintage = vintage;
    this.type = type;
    this.link = link;
    this.image = image;
}




    public String getName() {
        return name;
    }

    public String getWinery() {
        return winery;
    }

    public String getVarietal() {
        return varietal;
    }

    public String getPrice() {
        return price;
    }

    public String getVintage() {
        return vintage;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }
}
