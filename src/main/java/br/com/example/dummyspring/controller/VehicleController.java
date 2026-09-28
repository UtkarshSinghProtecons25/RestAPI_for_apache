package br.com.example.dummyspring.controller;

import br.com.example.dummyspring.model.dto.VehicleRequest;
import br.com.example.dummyspring.model.dto.VehicleResponse;
import br.com.example.dummyspring.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse create(
            @Valid @RequestBody VehicleRequest request,
            Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());

        try {
            return vehicleService.create(request, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping
    public List<VehicleResponse> getVehicles(
            Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());

        return vehicleService.getByUserId(userId);
    }


    @GetMapping("/{vin}")
    public VehicleResponse get(
            @PathVariable
            @Size(min = 17, max = 17)
            String vin,
            Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());

        return vehicleService.getByVin(vin);
    }
}