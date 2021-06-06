package com.alicja.restaurants.dto;

import java.util.Objects;
public class ResearchResponseDto {


    private String id;


    private String name;


    private String address;


    private String rating;

    private int priceLevel;


    private String photo;

    public ResearchResponseDto(String id, String name, String address, String rating, int price_leve, String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.priceLevel = price_leve;
        this.photo = photo;
    }

    public ResearchResponseDto() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "ResearchResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating='" + rating + '\'' +
                ", price_leve=" + priceLevel +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchResponseDto that = (ResearchResponseDto) o;
        return priceLevel == that.priceLevel && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(rating, that.rating) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rating, priceLevel, photo);
    }
}

