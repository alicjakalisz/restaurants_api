package com.alicja.restaurants.dto;


import java.util.Objects;

public class ResearchResponseDto {


    private long id;


    private String name;


    private String address;


    private String rating;

    private int price_leve;


    private String photo;

    public ResearchResponseDto(long id, String name, String address, String rating, int price_leve, String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price_leve = price_leve;
        this.photo = photo;
    }

    public ResearchResponseDto() {
    }

    public long getId() {
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

    public int getPrice_leve() {
        return price_leve;
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
                ", price_leve=" + price_leve +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResearchResponseDto that = (ResearchResponseDto) o;
        return id == that.id && price_leve == that.price_leve && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(rating, that.rating) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rating, price_leve, photo);
    }
}

