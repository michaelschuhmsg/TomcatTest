package com.example.tomcat_artefact.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.tomcat_artefact.entity.ShoppinglistEntry;
import com.example.tomcat_artefact.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController

public class EntriesController {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @GetMapping("/shoppinglist/entries")
    public ResponseEntity<List<ShoppinglistEntryDto>> getEntries() {

        List<ShoppinglistEntry> entries = shoppingListRepository.findAll();

        List<ShoppinglistEntryDto> result = entries.stream()
            .map(entity -> new ShoppinglistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
            .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/shoppinglist/entries")
    public ResponseEntity<?> postEntry(@RequestBody ShoppinglistEntryDto payload) {
        ShoppinglistEntry entity = new ShoppinglistEntry();
        entity.setTitle(payload.title());
        entity.setAmount(payload.amount());
        entity.setCategory(payload.category());

        entity = shoppingListRepository.save(entity);

        URI location = UriComponentsBuilder.fromUriString("/shoppinglist/entries/{id}")
            .build(entity.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shoppinglist/entries/{id}")
    public ResponseEntity<ShoppinglistEntryDto> getSingle(@PathVariable String id) {
        return shoppingListRepository.findById(id)
            .map(entity -> new ShoppinglistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/shoppinglist/entries/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        shoppingListRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/shoppinglist/entries/{id}")
    public ResponseEntity<ShoppinglistEntryDto> update(@PathVariable String id, @RequestBody ShoppinglistEntryDto payload) {
        Optional<ShoppinglistEntry> shoppinglistEntry = shoppingListRepository.findById(id)
            .map(entity -> {
                entity.setTitle(payload.title());
                entity.setAmount(payload.amount());
                entity.setCategory(payload.category());

                return entity;
            });

        if (shoppinglistEntry.isPresent()) {
            ShoppinglistEntry savedEntity = shoppingListRepository.save(shoppinglistEntry.get());

            return ResponseEntity.ok(new ShoppinglistEntryDto(savedEntity.getTitle(), savedEntity.getAmount(), savedEntity.getCategory()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

