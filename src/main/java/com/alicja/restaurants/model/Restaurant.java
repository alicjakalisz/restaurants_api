package com.alicja.restaurants.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String address;

    @Column
    private String rating;

    @Column
    private int price_level;

    @Column
    private String photo;

    @Column
    private String website;

    @Column
    private String user_rating_total;

    @Column
    private String phone_number;

    @Column
    private String comments;

    public Restaurant(long id, String address, String rating, int price_level, String photo, String website, String user_rating_total, String phone_number, String comments) {
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.price_level = price_level;
        this.photo = photo;
        this.website = website;
        this.user_rating_total = user_rating_total;
        this.phone_number = phone_number;
        this.comments = comments;
    }

    public Restaurant() {
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public int getPrice_level() {
        return price_level;
    }

    public String getPhoto() {
        return photo;
    }

    public String getWebsite() {
        return website;
    }

    public String getUser_rating_total() {
        return user_rating_total;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", address=" + address +
                ", rating='" + rating + '\'' +
                ", price_level=" + price_level +
                ", photo='" + photo + '\'' +
                ", website='" + website + '\'' +
                ", user_rating_total='" + user_rating_total + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && address == that.address && price_level == that.price_level && Objects.equals(rating, that.rating) && Objects.equals(photo, that.photo) && Objects.equals(website, that.website) && Objects.equals(user_rating_total, that.user_rating_total) && Objects.equals(phone_number, that.phone_number) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, rating, price_level, photo, website, user_rating_total, phone_number, comments);
    }
}
