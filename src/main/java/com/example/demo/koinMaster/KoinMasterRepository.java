package com.example.demo.koinMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KoinMasterRepository extends JpaRepository<KoinMaster, Long> {


}
