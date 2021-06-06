package com.alicja.restaurants.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;
import java.util.Optional;

public class RestaurantDto {



    private String id;


    private String address;


    private String rating;


    private Optional<Integer> priceLevel;


    private String photo;


    private String website;


    private String userRatingTotal;


    private String phoneNumber;


    private String comments;

    public RestaurantDto(String id, String address, String rating, Optional<Integer> priceLevel, String photo, String website, String userRatingTotal, String phoneNumber, String comments) {
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.priceLevel = priceLevel;
        this.photo = photo;
        this.website = website;
        this.userRatingTotal = userRatingTotal;
        this.phoneNumber = phoneNumber;
        this.comments = comments;
    }

    public RestaurantDto() {
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getRating() {
        return rating;
    }

    public Optional<Integer> getPriceLevel() {
        return priceLevel;
    }

    public String getPhoto() {
        return photo;
    }

    public String getWebsite() {
        return website;
    }

    public String getUserRatingTotal() {
        return userRatingTotal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPriceLevel(Optional<Integer> priceLevel) {
        this.priceLevel = priceLevel;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setUserRatingTotal(String userRatingTotal) {
        this.userRatingTotal = userRatingTotal;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", address=" + address +
                ", rating='" + rating + '\'' +
                ", price_level=" + priceLevel +
                ", photo='" + photo + '\'' +
                ", website='" + website + '\'' +
                ", user_rating_total='" + userRatingTotal + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return priceLevel.equals(that.priceLevel) && Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(rating, that.rating) && Objects.equals(photo, that.photo) && Objects.equals(website, that.website) && Objects.equals(userRatingTotal, that.userRatingTotal) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, rating, priceLevel, photo, website, userRatingTotal, phoneNumber, comments);
    }
}
