package com.example.couponsystem.service;

import com.example.couponsystem.entity.Coupon;
import com.example.couponsystem.producer.CouponCreateProducer;
import com.example.couponsystem.repository.ApplyUserRepository;
import com.example.couponsystem.repository.CouponCountRepository;
import com.example.couponsystem.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final ApplyUserRepository applyUserRepository;
    public void apply(Long userId) {
        Long apply = applyUserRepository.add(userId);
        if(apply != 1) {
            return;
        }

        Long count = couponCountRepository.increment();
        if(count > 100) {
            return;
        }
        couponCreateProducer.create(userId);
    }
}
