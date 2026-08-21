package br.com.example.davidarchanjo.controller;

import br.com.example.davidarchanjo.model.dto.LenderResponse;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.time.*;

@RestController
@RequestMapping("/api/v1/lenders")
public class LenderController {
    @GetMapping("/{id}")
    public LenderResponse get(@PathVariable String id) {
        return LenderResponse.builder().lenderId(id).lenderName("ABC Auto Finance").lenderAccountNumber("ACC-987654321").loanStatus("ACTIVE").originalLoanAmount(new BigDecimal("35000.00")).remainingPrincipal(new BigDecimal("18750.00")).payoffAmount(new BigDecimal("19025.50")).monthlyEmi(new BigDecimal("625.00")).emisPaid(28).totalEmis(60).lastPaymentId("PAY-100042").lastPaymentDate(LocalDate.of(2026, 7, 15)).currency("USD").build();
    }
}
