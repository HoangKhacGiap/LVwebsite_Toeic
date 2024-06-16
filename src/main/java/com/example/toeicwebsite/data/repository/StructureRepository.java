package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure, Long>{
    List<Structure> findAllByKinfOfStructureId(Long kindStructureId);
}
