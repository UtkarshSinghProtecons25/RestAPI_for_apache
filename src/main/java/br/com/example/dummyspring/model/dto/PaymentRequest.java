package br.com.example.dummyspring.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal paymentAmount;

    @NotNull
    @Valid
    private CustomerRef customer;

    @NotNull
    @Valid
    private VehicleRef vehicle;

    @NotNull
    @Valid
    private LenderRef lender;

    @Data
    public static class CustomerRef {

        @NotBlank
        private String customerId;
    }
    @Data
    public static class VehicleRef {

        @NotBlank
        private String vehicleId;

        @NotBlank
        private String vin;
    }

    @Data
    public static class LenderRef {
        private String lenderId, lenderName;
        private BigDecimal payoffAmount;
    }
}
