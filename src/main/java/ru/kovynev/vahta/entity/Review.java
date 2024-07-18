package ru.kovynev.vahta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10000)
    private String text;
    private String name;
    @OneToOne
    private Rating rating;
   @ManyToOne
    private Company company;


}
