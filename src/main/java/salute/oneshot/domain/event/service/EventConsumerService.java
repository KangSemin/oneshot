package salute.oneshot.domain.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.coupon.dto.service.CreateUserCpnSDto;
import salute.oneshot.domain.coupon.service.CouponService;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventConsumerService {

    private final CouponService couponService;
    private final EventNotificationService eventNotificationService;

    @RabbitListener(queues = "${rabbitmq.queue.event}")
    public void processEventWinner(ParticipateEventDto participateDto) {
        log.info("이벤트 당첨자 처리: {}", participateDto);

        try {
            Long couponId = participateDto.getCouponId();
            log.info("쿠폰 발급 시도 - 쿠폰ID: {}, 사용자ID: {}", couponId, participateDto.getUserId());

            CreateUserCpnSDto createUserCpnSDto =
                    CreateUserCpnSDto.of(couponId, participateDto.getUserId());
            couponService.grantUserCoupon(createUserCpnSDto);

            log.info("이벤트 당첨자 처리 완료: {}", participateDto);
        } catch (Exception e) {
            log.error("이벤트 당첨자 처리 실패: {}", participateDto, e);
        }
    }
}