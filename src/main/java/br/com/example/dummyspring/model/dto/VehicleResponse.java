package br.com.example.dummyspring.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private String vin, vehicleId, make, model, trim, color, vehicleType, lenderId, titleStatus, registrationState;
    private Integer year;
    private Boolean totalLoss;
}
