package br.com.example.dummyspring.service;

import br.com.example.dummyspring.exception.PaymentNotFoundException;
import br.com.example.dummyspring.exception.VehicleNotFoundException;
import br.com.example.dummyspring.model.domain.Payment;
import br.com.example.dummyspring.model.domain.Vehicle;
import br.com.example.dummyspring.model.dto.PaymentRequest;
import br.com.example.dummyspring.model.dto.PaymentResponse;
import br.com.example.dummyspring.repository.PaymentRepository;
import br.com.example.dummyspring.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final VehicleRepository vehicleRepository;

    public PaymentResponse pay(
            PaymentRequest request,
            Long userId) {

        // 1. Find vehicle belonging to logged-in user
        Vehicle vehicle = vehicleRepository
                .findByVinAndUserId(
                        request.getVehicle().getVin(),
                        userId
                )
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found for the logged-in user"
                        )
                );

        // 2. Validate VIN
        if (!vehicle.getVin().equals(request.getVehicle().getVin())) {
            throw new RuntimeException(
                    "Vehicle ID and VIN do not match"
            );
        }

        // 3. Vehicle must be total loss
        if (!Boolean.TRUE.equals(vehicle.getTotalLoss())) {
            throw new RuntimeException(
                    "Vehicle must be total loss"
            );
        }


        // 6. Generate payment ID
        String paymentId =
                "PAY-" + (100001 + paymentRepository.count());

        // 7. Create payment
        Payment payment = Payment.builder()
                .paymentId(paymentId)
                .amount(request.getPaymentAmount())

                // Customer ID can simply come from request
                .customerId(
                        request.getCustomer().getCustomerId()
                )

                // Vehicle information comes from DB
                .vehicleId(vehicle.getVehicleId())
                .vin(vehicle.getVin())

//                // Lender information comes from DB
                .lenderId(request.getLender().getLenderId())
                .lenderName(request.getLender().getLenderName())

                .status("SUCCESS")
                .paymentDate(Instant.now())

                // IMPORTANT: user comes from JWT
                .userId(userId)

                .build();

        paymentRepository.save(payment);

        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .status(payment.getStatus())
                .paymentAmount(payment.getAmount())
                .currency("USD")
                .customerId(payment.getCustomerId())
                .vehicleId(payment.getVehicleId())
                .vin(payment.getVin())
                .lenderId(payment.getLenderId())
                .lenderName(payment.getLenderName())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
    public Payment getPaymentDetails(String lenderId) {

        return paymentRepository.findTopByLenderIdOrderByIdDesc(lenderId)
                .orElseThrow(() ->
                        new PaymentNotFoundException(
                                "No payment details found for lenderId: " + lenderId
                        )
                );
    }
}