package ru.kovynev.vahta.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovynev.vahta.entity.UserEntity;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<UserEntity> findUserEntitiesByPublication(Boolean boll);

}
