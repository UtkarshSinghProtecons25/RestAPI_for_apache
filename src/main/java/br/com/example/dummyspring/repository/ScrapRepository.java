package br.com.example.dummyspring.repository;

import br.com.example.dummyspring.model.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
}
