package salute.oneshot.domain.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.response.EventPageResponseDto;
import salute.oneshot.domain.event.dto.response.ParticipateEventDto;
import salute.oneshot.domain.event.dto.service.GetEventsSDto;
import salute.oneshot.domain.event.dto.service.ParticipateEventSDto;
import salute.oneshot.domain.event.service.EventService;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<EventPageResponseDto>> getEvents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by("endTime").ascending()
        );
        GetEventsSDto serviceDto = GetEventsSDto.of(
                status, type, startDate, endDate, pageable);
        EventPageResponseDto responseDto =
                eventService.getEvents(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_EVT_LIST_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<EventDetailResponseDto>> getEvent(
            @PathVariable Long eventId
    ) {
        EventDetailResponseDto responseDto =
                eventService.getEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_EVT_SUCCESS,
                        responseDto));
    }

    @PostMapping("/{eventId}/participation")
    public ResponseEntity<ApiResponse<ParticipateEventDto>> participateEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ParticipateEventSDto serviceDto =
                ParticipateEventSDto.of(eventId, userDetails.getId());
        ParticipateEventDto responseDto =
                eventService.participateEvent(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.PRT_EVENT_SUCCESS,
                        responseDto));
    }
}