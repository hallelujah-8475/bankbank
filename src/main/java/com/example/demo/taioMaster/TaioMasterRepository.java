package com.example.demo.taioMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaioMasterRepository extends JpaRepository<TaioMaster, Long> {
}
