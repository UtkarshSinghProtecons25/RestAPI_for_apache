package br.com.example.dummyspring.controller;

import br.com.example.dummyspring.model.domain.Payment;
import br.com.example.dummyspring.model.dto.*;
import br.com.example.dummyspring.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentRepository repo;
    private static final BigDecimal PAYOFF = new BigDecimal("19025.50");

    @GetMapping("/{lenderId}")
    public Map<String, Object> get(@PathVariable String lenderId) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("lenderId", lenderId);
        m.put("paymentStatus", "PARTIALLY_PAID");
        m.put("lastPaymentId", "PAY-100042");
        m.put("lastPaymentAmount", 625.00);
        m.put("lastPaymentDate", "2026-07-15");
        m.put("totalPaymentsMade", 28);
        m.put("totalAmountPaid", 17500.00);
        m.put("remainingPayoffAmount", 19025.50);
        return m;
    }

    @PostMapping
    public ResponseEntity<?> pay(@Valid @RequestBody PaymentRequest r) {
        if (!Boolean.TRUE.equals(r.getVehicle().getTotalLoss()))
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Vehicle must be total loss"));
        if (r.getPaymentAmount().compareTo(PAYOFF) != 0)
            return ResponseEntity.badRequest().body(Collections.singletonMap("errorCode", "PAYOFF_AMOUNT_MISMATCH"));
        String id = "PAY-" + (100001 + repo.count());
        Payment p = Payment.builder().paymentId(id).amount(r.getPaymentAmount()).customerId(r.getCustomer().getCustomerId()).vehicleId(r.getVehicle().getVehicleId()).vin(r.getVehicle().getVin()).lenderId(r.getLender().getLenderId()).lenderName(r.getLender().getLenderName()).status("SUCCESS").paymentDate(Instant.now()).build();
        repo.save(p);
        return ResponseEntity.status(201).body(PaymentResponse.builder().paymentId(id).status("SUCCESS").paymentAmount(p.getAmount()).currency("USD").customerId(p.getCustomerId()).vehicleId(p.getVehicleId()).vin(p.getVin()).lenderId(p.getLenderId()).lenderName(p.getLenderName()).paymentDate(p.getPaymentDate()).build());
    }
}
