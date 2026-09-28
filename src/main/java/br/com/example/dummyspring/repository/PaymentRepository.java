package br.com.example.dummyspring.repository;

import br.com.example.dummyspring.model.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findTopByLenderIdOrderByIdDesc(String lenderId);

    List<Payment> findByUserId(Long userId);

    Optional<Payment> findByPaymentIdAndUserId(
            String paymentId,
            Long userId
    );

    Optional<Payment> findByVehicleIdAndUserId(
            String vehicleID,
            Long userId
    );
}