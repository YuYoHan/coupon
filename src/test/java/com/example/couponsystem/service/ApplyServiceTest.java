package com.example.couponsystem.service;

import com.example.couponsystem.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 발급 테스트")
    void 한번만응모() {
        applyService.apply(1L);
        long count = couponRepository.count();
        Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    void 여러명응모() throws InterruptedException {
        int threadCount = 1000;
        // Executors는 병렬 작업을 간단하게 할 수 있게 도와주는 JAVA API
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        // CountDownLatch는 다른 thread에서 수행하는 작업을 기다리도록 도와주는 클래스
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.apply(userId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        long count = couponRepository.count();
        Assertions.assertThat(count).isEqualTo(100);
    }

}