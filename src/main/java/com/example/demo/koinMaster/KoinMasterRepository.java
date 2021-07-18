package com.example.demo.koinMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface KoinMasterRepository extends JpaRepository<KoinMaster, Integer>, JpaSpecificationExecutor<KoinMaster> {

	KoinMaster findById(int id);

	KoinMaster findByKoinname(String koinname);
}
