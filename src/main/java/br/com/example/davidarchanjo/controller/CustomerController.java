package br.com.example.davidarchanjo.controller;

import br.com.example.davidarchanjo.model.domain.Customer;
import br.com.example.davidarchanjo.model.dto.*;
import br.com.example.davidarchanjo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository repo;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest r) {
        if (repo.existsByEmail(r.getEmail())) return ResponseEntity.status(409).build();
        long n = repo.count() + 100001;
        Customer c = Customer.builder().customerId("CUS-" + n).firstName(r.getFirstName()).lastName(r.getLastName()).email(r.getEmail()).phone(r.getPhone()).street(r.getAddress() == null ? null : r.getAddress().getStreet()).city(r.getAddress() == null ? null : r.getAddress().getCity()).state(r.getAddress() == null ? null : r.getAddress().getState()).zipCode(r.getAddress() == null ? null : r.getAddress().getZipCode()).status("ACTIVE").createdAt(Instant.now()).build();
        repo.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.builder().customerId(c.getCustomerId()).firstName(c.getFirstName()).lastName(c.getLastName()).email(c.getEmail()).phone(c.getPhone()).status(c.getStatus()).createdAt(c.getCreatedAt()).build());
    }
}
