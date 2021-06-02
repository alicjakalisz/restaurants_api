package com.alicja.restaurants.model;



import javax.persistence.*;

@Entity
public class ResearchResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String rating;

    @Column
    private int price_leve;

    @Column
    private String photo;

    public ResearchResponse(long id, String name, String address, String rating, int price_leve, String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.price_leve = price_leve;
        this.photo = photo;
    }

    public ResearchResponse() {
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
}
