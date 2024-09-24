package com.example.cunsumer.cunsumer;

import com.example.cunsumer.entity.Coupon;
import com.example.cunsumer.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponCreateConsumer {
    private final CouponRepository couponRepository;

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {

        Coupon coupon = Coupon.builder()
                .userId(userId)
                .build();
        couponRepository.save(coupon);
    }
}
