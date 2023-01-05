package com.aquavitta.aquavitta.repositories;

import com.aquavitta.aquavitta.models.RiverModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiverRepository extends JpaRepository<RiverModel, UUID> {
}
