package com.example.demo.repository;

import com.example.demo.model.MobilePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilePaymentRepository extends JpaRepository<MobilePayment,Long> {
}
