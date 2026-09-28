package br.com.example.dummyspring.controller;

import br.com.example.dummyspring.model.domain.Payment;
import br.com.example.dummyspring.model.dto.*;
import br.com.example.dummyspring.repository.PaymentRepository;
import br.com.example.dummyspring.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentRepository repo;
    private static final BigDecimal PAYOFF = new BigDecimal("19025.50");

    private final PaymentService paymentService;

    @GetMapping("/{lenderId}")
    public Payment get(@PathVariable String lenderId) {

        return paymentService.getPaymentDetails(lenderId);
    }

//    @PostMapping
//    public ResponseEntity<?> pay(@Valid @RequestBody PaymentRequest r) {
//        if (!Boolean.TRUE.equals(r.getVehicle().getTotalLoss()))
//            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vehicle must be total loss"));
//        if (r.getPaymentAmount().compareTo(PAYOFF) != 0)
//            return ResponseEntity.badRequest().body(Collections.singletonMap("errorCode", "PAYOFF_AMOUNT_MISMATCH"));
//        String id = "PAY-" + (100001 + repo.count());
//        Payment p = Payment.builder().paymentId(id).amount(r.getPaymentAmount()).customerId(r.getCustomer().getCustomerId()).vehicleId(r.getVehicle().getVehicleId()).vin(r.getVehicle().getVin()).lenderId(r.getLender().getLenderId()).lenderName(r.getLender().getLenderName()).status("SUCCESS").paymentDate(Instant.now()).build();
//        repo.save(p);
//        return ResponseEntity.status(201).body(PaymentResponse.builder().paymentId(id).status("SUCCESS").paymentAmount(p.getAmount()).currency("USD").customerId(p.getCustomerId()).vehicleId(p.getVehicleId()).vin(p.getVin()).lenderId(p.getLenderId()).lenderName(p.getLenderName()).paymentDate(p.getPaymentDate()).build());
//    }

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(
            @Valid @RequestBody PaymentRequest request,
            Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());

        PaymentResponse response =
                paymentService.pay(request, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
