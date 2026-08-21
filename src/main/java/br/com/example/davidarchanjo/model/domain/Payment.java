package br.com.example.davidarchanjo.model.domain;
import lombok.*; import javax.persistence.*; import java.math.BigDecimal; import java.time.Instant;
@Data @Entity @NoArgsConstructor @AllArgsConstructor @Builder public class Payment { @Id @GeneratedValue private Long id; @Column(unique=true) private String paymentId; private BigDecimal amount; private String customerId; private String vehicleId; private String vin; private String lenderId; private String lenderName; private String status; private Instant paymentDate; }
