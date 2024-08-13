package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure, Long>{
    List<Structure> findAllByKinfOfStructureId(Long kindStructureId);
    @Query("select count(u) from Structure u")
    long countStructureCreate();
}
