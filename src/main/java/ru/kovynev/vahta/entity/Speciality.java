package ru.kovynev.vahta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Speciality {
    @Id
    private Long id;
    private String name;

    public Speciality(BuilderSpeciality builderSpeciality) {
        this.id = builderSpeciality.id;
        this.name = builderSpeciality.name;

    }

    public Speciality() {
    }

    public static BuilderSpeciality build(){
        return new BuilderSpeciality();
    }

    public static class BuilderSpeciality {
        private Long id;
        private String name;


        public BuilderSpeciality id(Long id) {
            this.id = id;
            return this;
        }

        public BuilderSpeciality name(String name) {
            this.name = name;
            return this;
        }

        public Speciality build() {
            return new Speciality(this);
        }

    }
}
