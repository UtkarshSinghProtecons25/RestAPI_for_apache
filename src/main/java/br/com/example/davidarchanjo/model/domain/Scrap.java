package br.com.example.davidarchanjo.model.domain;
import lombok.*; import javax.persistence.*; import java.time.Instant;
@Data @Entity @NoArgsConstructor @AllArgsConstructor @Builder public class Scrap { @Id @GeneratedValue private Long id; @Column(unique=true) private String scrapId; private String customerId; private String vehicleId; private String vin; private String lenderId; private String paymentId; private String status; private Instant createdAt; }
