package br.com.example.dummyspring.service;

import java.util.List;
import java.util.Optional;

import br.com.example.dummyspring.model.dto.AppDTO;

public interface AppService {

    Long createNewApp(AppDTO dto);

    List<Optional<AppDTO>> getAllApps();

    Optional<AppDTO> getAppById(Long id);

    Optional<AppDTO> updateApp(Long id, AppDTO dto);

    void deleteAppById(Long id);

    void populate();
}
