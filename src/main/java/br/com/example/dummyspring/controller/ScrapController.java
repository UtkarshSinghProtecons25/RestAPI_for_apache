package br.com.example.dummyspring.controller;

import br.com.example.dummyspring.model.domain.*;
import br.com.example.dummyspring.model.dto.*;
import br.com.example.dummyspring.repository.*;
import br.com.example.dummyspring.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/scraps")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;
    @PostMapping
    public ResponseEntity<ScrapResponse> scrap(
            @Valid @RequestBody ScrapRequest request,
            Authentication authentication) {

        Long userId = Long.valueOf(authentication.getName());

        ScrapResponse response =
                scrapService.scrap(request.getActionOnVehicle(), userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
