package com.example.demo.keiyakuMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeiyakuMasterRepository extends JpaRepository<KeiyakuMaster, Long> {

	public int countByShoninflg(int shoninflg);

}
