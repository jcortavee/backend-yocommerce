package com.seminario.store.repositories;

import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

    @Query("SELECT dp FROM DeliveryPerson dp WHERE dp.user.id = ?1")
    Optional<DeliveryPerson> findByUserId(Long userId);
}
