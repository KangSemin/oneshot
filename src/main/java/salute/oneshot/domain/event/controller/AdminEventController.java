package salute.oneshot.domain.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
public class AdminEventController {

    private final EventService eventService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<EventBriefResponseDto>> createEvent(
            @RequestBody EventRequestDto requestDto
    ) {
        CreateEventSDto serviceDto = CreateEventSDto.of(
                requestDto.getName(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime(),
                requestDto.getEventType(),
                requestDto.getEventDetail());
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

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponseDto>> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequestDto requestDto
    ) {
        UpdateEventSDto serviceDto = UpdateEventSDto.of(
                eventId,
                requestDto.getName(),
                requestDto.getStartDate(),
                requestDto.getStartTime(),
                requestDto.getEndDate(),
                requestDto.getEndTime(),
                requestDto.getEventType(),
                requestDto.getEventDetail());
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Long>> deleteEvent(
            @PathVariable Long eventId
    ) {
        Long deletedId = eventService.deleteEvent(eventId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_EVENT_SUCCESS,
                        deletedId));
    }

    private void validateEventDate(LocalDateTime startTime, LocalDateTime endTime) {
        if (endTime.isBefore(LocalDateTime.now())) {
            throw new InvalidException(ErrorCode.EXPIRED_EVENT);
        }

        if (startTime.isAfter(endTime)) {
            throw new InvalidException(ErrorCode.INVALID_EVENT_PERIOD);
        }
    }
}
