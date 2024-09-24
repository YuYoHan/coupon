package com.example.cunsumer.cunsumer;

import com.example.cunsumer.entity.Coupon;
import com.example.cunsumer.entity.FailedEvent;
import com.example.cunsumer.repository.CouponRepository;
import com.example.cunsumer.repository.FailedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.retry.annotation.Retryable;


@Component
@RequiredArgsConstructor
@Log4j2
@Transactional // 모든 처리를 트랜잭션으로 묶음
public class CouponCreateConsumer {
    private final CouponRepository couponRepository;
    private final FailedEventRepository failedEventRepository;


    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000)) // 재시도 로직
    public void listener(Long userId) {
        try {
            Coupon coupon = Coupon.builder()
                    .userId(userId)
                    .build();
            couponRepository.save(coupon);
        } catch (Exception e) {
            log.error("failed to create coupon {}", userId);
            FailedEvent failedCoupon = FailedEvent.builder()
                    .userId(userId)
                    .build();
            failedEventRepository.save(failedCoupon);
            throw new RuntimeException();
        }
    }
}
