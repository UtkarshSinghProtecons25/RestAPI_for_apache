package br.com.example.dummyspring.service;

import br.com.example.dummyspring.exception.PaymentNotFoundException;
import br.com.example.dummyspring.exception.VehicleNotFoundException;
import br.com.example.dummyspring.model.domain.Payment;
import br.com.example.dummyspring.model.domain.Scrap;
import br.com.example.dummyspring.model.domain.Vehicle;
import br.com.example.dummyspring.model.dto.ScrapResponse;
import br.com.example.dummyspring.repository.PaymentRepository;
import br.com.example.dummyspring.repository.ScrapRepository;
import br.com.example.dummyspring.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class ScrapService {


    private  final PaymentRepository paymentRepository;
    private final VehicleRepository vehicleRepository;

    private  final ScrapRepository scrapRepository;

    public ScrapResponse scrap(String actionOnVehicle, Long userId) {

        if (!"scrap".equalsIgnoreCase(actionOnVehicle)) {
            throw new IllegalArgumentException(
                    "Invalid actionOnVehicle. Expected 'scrap'"
            );
        }

        Vehicle vehicle = vehicleRepository
                .findByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "No vehicle exists for the logged-in user"
                        )
                );

        Payment payment = paymentRepository
                .findByVehicleIdAndUserId(
                        vehicle.getVehicleId(),
                        userId
                )
                .orElseThrow(() ->
                        new PaymentNotFoundException(
                                "No payment exists for the vehicle"
                        )
                );

        // Create scrap using vehicle + payment database data
        Scrap scrap = Scrap.builder()
                .scrapId("SCRAP-" + (100001 + scrapRepository.count()))
                .userId(userId)
                .vehicleId(vehicle.getVehicleId())
                .paymentId(payment.getPaymentId())
                .customerId(userId.toString())
                .status("SCRAP_APPROVED")
                .vin(vehicle.getVin())
                .lenderId(payment.getLenderId())
                .createdAt(Instant.now())
                .build();

        scrapRepository.save(scrap);

        return ScrapResponse.builder()
                .scrapId(scrap.getScrapId())
                .status(scrap.getStatus())
                .customerId(scrap.getCustomerId())
                .vehicleId(scrap.getVehicleId())
                .paymentId(scrap.getPaymentId())
                .createdAt(scrap.getCreatedAt())
                .build();
    }
}
