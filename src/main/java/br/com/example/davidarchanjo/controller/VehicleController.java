package br.com.example.davidarchanjo.controller;

import br.com.example.davidarchanjo.model.dto.VehicleRequest;
import br.com.example.davidarchanjo.model.dto.VehicleResponse;
import br.com.example.davidarchanjo.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

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

    @GetMapping("/{vin}")
    public VehicleResponse get(
            @PathVariable @Size(min = 17, max = 17) String vin,
            Authentication authentication) {
        return vehicleService.getByVin(vin);
    }
}