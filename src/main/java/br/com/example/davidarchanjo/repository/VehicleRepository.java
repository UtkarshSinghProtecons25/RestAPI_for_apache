package br.com.example.davidarchanjo.repository;

import br.com.example.davidarchanjo.model.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByVin(String vin);

    boolean existsByVin(String vin);
}