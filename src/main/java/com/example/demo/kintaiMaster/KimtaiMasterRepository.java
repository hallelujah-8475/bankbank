package com.example.demo.kintaiMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KimtaiMasterRepository extends JpaRepository<KintaiMaster, Long> {
}
