package com.example.tomcat_artefact.repository;

import com.example.tomcat_artefact.entity.ShoppinglistEntry;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  ShoppingListRepository extends JpaRepository<ShoppinglistEntry, String> {
    }

