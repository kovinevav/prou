package ru.kovynev.vahta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Column(length = 100)
    private String anons;
    @Column(length = 10000)
    private String text;
    private String web;
    private String headHunter;



    @OneToMany
    private List<Contact> contacts = new ArrayList<>();
    @OneToMany
    private List<Review> reviewList = new ArrayList<>();
    public void setReview(Review review) {
        this.reviewList.add(review);
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", anons='" + anons + '\'' +
                ", text='" + text + '\'' +
                ", contacts=" + contacts +
                ", WEB=" + web +
                ", reviewList=" + reviewList +
                '}';
    }
}
