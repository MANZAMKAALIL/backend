package com.example.demo.service;

import com.example.demo.model.MobilePayment;
import com.example.demo.repository.MobilePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilePaymentService {
    @Autowired
    private MobilePaymentRepository mobilePaymentRepository;
     public List<MobilePayment> getAllPayments(){
         return mobilePaymentRepository.findAll();
     }
     public Optional<MobilePayment> getPaymentById(Integer mobilePaymentId){
         return mobilePaymentRepository.findById(Long.valueOf(mobilePaymentId));
     }
    public MobilePayment savePayment(MobilePayment mobilePayment) {
        mobilePaymentRepository.save(mobilePayment);
        return mobilePayment;
    }

    public void cancelPayment(Integer mobilePaymentId) {
        mobilePaymentRepository.deleteById(Long.valueOf(mobilePaymentId));
    }

}

