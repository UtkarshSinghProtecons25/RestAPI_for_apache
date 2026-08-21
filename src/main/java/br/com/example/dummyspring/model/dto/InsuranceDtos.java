package br.com.example.dummyspring.model.dto; import lombok.*; import javax.validation.constraints.*; import java.math.BigDecimal; import java.time.*;
public class InsuranceDtos {
 @Data public static class AddressDto{private String street,city,state,zipCode;}
 @Data public static class CustomerRequest{@NotBlank private String firstName,lastName; @NotBlank @Email private String email; @NotBlank private String phone; private AddressDto address;}
 @Data @Builder public static class CustomerResponse{private String customerId,firstName,lastName,email,phone,status; private Instant createdAt;}
 @Data @Builder public static class VehicleResponse{private String vin,vehicleId,make,model,trim,color,vehicleType,lenderId,titleStatus,registrationState; private Integer year; private Boolean totalLoss;}
 @Data @Builder public static class LenderResponse{private String lenderId,lenderName,lenderAccountNumber,loanStatus,lastPaymentId,currency; private BigDecimal originalLoanAmount,remainingPrincipal,payoffAmount,monthlyEmi; private Integer emisPaid,totalEmis; private LocalDate lastPaymentDate;}
 @Data @Builder public static class PaymentLookupResponse{private String lenderId,paymentStatus,lastPaymentId; private BigDecimal lastPaymentAmount,totalAmountPaid,remainingPayoffAmount; private LocalDate lastPaymentDate; private Integer totalPaymentsMade;}
 @Data public static class CustomerRef{private String customerId,firstName,lastName,email,phone;}
 @Data public static class VehicleRef{private String vin,vehicleId,make,model,lenderId; private Integer year; private Boolean totalLoss;}
 @Data public static class LenderRef{private String lenderId,lenderName,lenderAccountNumber,loanStatus; private BigDecimal payoffAmount;}
 @Data public static class PaymentRequest{@NotNull @DecimalMin("0.01") private BigDecimal paymentAmount; @NotNull private CustomerRef customer; @NotNull private VehicleRef vehicle; @NotNull private LenderRef lender;}
 @Data @Builder public static class PaymentResponse{private String paymentId,status,currency,customerId,vehicleId,vin,lenderId,lenderName; private BigDecimal paymentAmount; private Instant paymentDate;}
 @Data public static class DamageDto{@NotBlank private String damageType,damageSeverity; @NotNull private BigDecimal estimatedDamageAmount; private String description; private Boolean airbagsDeployed,vehicleOperable;}
 @Data public static class ScrapRequest{@NotNull private DamageDto damage; @NotNull private CustomerRef customer; @NotNull private VehicleRef vehicle; @NotNull private LenderRef lender;}
 @Data @Builder public static class ScrapResponse{private String scrapId,status,customerId,paymentId,vehicleId; private Instant createdAt;}
}
