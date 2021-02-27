package com.example.demo.clientMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientMasterRepository extends JpaRepository<ClientMaster, Long> {
}
