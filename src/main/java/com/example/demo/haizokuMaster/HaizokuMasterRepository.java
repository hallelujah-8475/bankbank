package com.example.demo.haizokuMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HaizokuMasterRepository extends JpaRepository<HaizokuMaster, Long> {
}
