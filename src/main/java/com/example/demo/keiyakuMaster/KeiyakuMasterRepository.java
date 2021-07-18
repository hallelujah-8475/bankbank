package com.example.demo.keiyakuMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface KeiyakuMasterRepository extends JpaRepository<KeiyakuMaster, Integer>, JpaSpecificationExecutor<KeiyakuMaster> {

	public int countByShoninflg(int shoninflg);

	public KeiyakuMaster findById(int id);

}
