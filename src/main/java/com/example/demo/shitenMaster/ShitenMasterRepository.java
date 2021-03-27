package com.example.demo.shitenMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ShitenMasterRepository extends JpaRepository<ShitenMaster, Long>, JpaSpecificationExecutor<ShitenMaster> {
}
