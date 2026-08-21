package br.com.example.dummyspring.service;

import br.com.example.dummyspring.model.domain.Vehicle;
import br.com.example.dummyspring.model.dto.VehicleRequest;
import br.com.example.dummyspring.model.dto.VehicleResponse;
import br.com.example.dummyspring.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository repository;

    @Transactional
    public VehicleResponse create(VehicleRequest request, Long userId) throws Exception {

        if (repository.existsByVin(request.getVin())) {
            throw new Exception("Vehicle with this VIN already exists");
        }
        String lenderId = request.getLenderId();

        if (lenderId == null || lenderId.trim().isEmpty()) {
            lenderId = generateLenderId();
        }

        Vehicle vehicle = Vehicle.builder()
                .vehicleId(generateVehicleId())
                .vin(request.getVin())
                .year(request.getYear())
                .make(request.getMake())
                .model(request.getModel())
                .trim(request.getTrim())
                .color(request.getColor())
                .vehicleType(request.getVehicleType())
                .totalLoss(request.getTotalLoss())
                .lenderId(lenderId)
                .titleStatus(request.getTitleStatus())
                .registrationState(request.getRegistrationState())
                .userId(userId)
                .createdAt(Instant.now())
                .build();

        return toResponse(repository.save(vehicle));
    }

    public VehicleResponse getByVin(String vin) {

        Vehicle vehicle = repository.findByVin(vin)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        return toResponse(vehicle);
    }

    private String generateVehicleId() {
        return "VEH-" + (100000 + repository.count() + 1);
    }

    private String generateLenderId() {
        return "LND-" + (100000 + repository.count() + 1);
    }

    private VehicleResponse toResponse(Vehicle vehicle) {

        return VehicleResponse.builder()
                .vehicleId(vehicle.getVehicleId())
                .vin(vehicle.getVin())
                .year(vehicle.getYear())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .trim(vehicle.getTrim())
                .color(vehicle.getColor())
                .vehicleType(vehicle.getVehicleType())
                .totalLoss(vehicle.getTotalLoss())
                .lenderId(vehicle.getLenderId())
                .titleStatus(vehicle.getTitleStatus())
                .registrationState(vehicle.getRegistrationState())
                .build();
    }
}