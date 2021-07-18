package com.example.demo.systemUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>, JpaSpecificationExecutor<SystemUser> {

	SystemUser findByLoginid(String loginid);

	SystemUser findById(int id);

	SystemUser findByKoinid(int koinid);

	public Page<SystemUser> findAll(Specification<SystemUser> specification, Pageable pageable);
}
