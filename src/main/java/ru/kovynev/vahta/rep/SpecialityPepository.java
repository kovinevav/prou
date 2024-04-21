package ru.kovynev.vahta.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovynev.vahta.entity.Speciality;

public interface SpecialityPepository  extends JpaRepository<Speciality, Long> {
}
