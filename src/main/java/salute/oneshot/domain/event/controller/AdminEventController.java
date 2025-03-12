package salute.oneshot.domain.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.event.dto.response.EventBriefResponseDto;
import salute.oneshot.domain.event.dto.request.EventRequestDto;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.service.CreateEventSDto;
import salute.oneshot.domain.event.dto.service.UpdateEventSDto;
import salute.oneshot.domain.event.service.EventService;
import salute.oneshot.global.exception.InvalidException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/events")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminEventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<EventBriefResponseDto>> createEvent(
            @Valid @RequestBody EventRequestDto requestDto
    ) {
        CreateEventSDto serviceDto = CreateEventSDto.of(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime(),
                requestDto.getEventType(),
                requestDto.getLimitCount(),
                requestDto.getEventDetailData());
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());
        EventBriefResponseDto responseDto =
                eventService.createEvent(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_EVENT_SUCCESS,
                        responseDto));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponseDto>> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody EventRequestDto requestDto
    ) {
        UpdateEventSDto serviceDto = UpdateEventSDto.of(
                eventId,
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime(),
                requestDto.getEventType(),
                requestDto.getEventDetailData());
        validateEventDate(
                serviceDto.getStartTime(),
                serviceDto.getEndTime());
        EventDetailResponseDto responseDto =
                eventService.updateEvent(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_EVENT_SUCCESS,
                        responseDto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Long>> deleteEvent(
            @PathVariable Long eventId
    ) {
        eventService.deleteEvent(eventId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_EVENT_SUCCESS,
                        eventId));
    }

    // 배너 클릭 -> 이벤트 GET 화면에서 SSE 구독
    @GetMapping("/event-stream/{eventId}")
    public SseEmitter streamEventUpdates(
            @PathVariable Long eventId
    ) {
        return eventService.subscribeEvent(eventId);
    }

    // 이벤트의 날짜 유효성 체크
    private void validateEventDate(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        if (endTime.isBefore(LocalDateTime.now())) {
            throw new InvalidException(ErrorCode.EXPIRED_EVENT);
        }

        if (startTime.isAfter(endTime)) {
            throw new InvalidException(ErrorCode.INVALID_EVENT_PERIOD);
        }
    }
}