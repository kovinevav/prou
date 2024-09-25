package ru.kovynev.vahta.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Speciality speciality;
    private Date date;
    @ManyToOne
    private Company company;
    private String description;

    public Vacancy() {
    }

    public Vacancy(VacancyBuilder vacancyBuilder) {
        this.speciality = vacancyBuilder.speciality;
        this.date = vacancyBuilder.date;
        this.company = vacancyBuilder.company;
        this.description = vacancyBuilder.description;

    }

    public static VacancyBuilder builder() {
        return new VacancyBuilder();

    }

    public static class VacancyBuilder {
        private Speciality speciality;
        private Date date;
        private Company company;
        private String description;

        public VacancyBuilder speciality(Speciality speciality) {
            this.speciality = speciality;
            return this;
        }

        public VacancyBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public VacancyBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public VacancyBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Vacancy build() {
            return new Vacancy(this);
        }
    }
}
