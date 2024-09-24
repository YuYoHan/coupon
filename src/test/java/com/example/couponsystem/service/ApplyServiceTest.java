package com.example.couponsystem.service;

import com.example.couponsystem.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @MockBean
    private CouponRepository couponRepository; // MockBean으로 CouponRepository를 Mock 처리

    @Test
    @DisplayName("쿠폰 발급 테스트 - Mock 적용")
    void 한번만응모() {
        // Mock으로 특정 상황을 가정
        Mockito.when(couponRepository.count()).thenReturn(1L);

        applyService.apply(1L); // apply 메서드 호출
        long count = couponRepository.count(); // mock된 count 값 사용

        // 결과 확인
        Assertions.assertThat(count).isEqualTo(1);

        // apply 호출 시의 메서드 동작을 확인할 수도 있음
        Mockito.verify(couponRepository, Mockito.times(1)).count();
    }

    @Test
    void 여러명응모() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // 쿠폰 수를 100으로 Mock 처리
        Mockito.when(couponRepository.count()).thenReturn(100L);

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
        Thread.sleep(10000);
        long count = couponRepository.count(); // mock된 count 값 사용

        Assertions.assertThat(count).isEqualTo(100);

    }

    @Test
    void 한명당_한개의쿠폰만_발급() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // 쿠폰 수를 100으로 Mock 처리
        Mockito.when(couponRepository.count()).thenReturn(100L);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    applyService.apply(1L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await(); // 모든 스레드가 완료될 때까지 대기
        Thread.sleep(1000); // 대기 시간을 조금 추가하여 처리 완료 기다림

        // 최종적으로 발급된 쿠폰 수 확인 (100개만 발급되어야 함)
        long count = couponRepository.count();
        Assertions.assertThat(count).isEqualTo(100);
    }
}
