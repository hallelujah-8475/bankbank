package com.example.demo.accesslog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

}
