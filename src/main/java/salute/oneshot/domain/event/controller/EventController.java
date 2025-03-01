package salute.oneshot.domain.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.event.dto.response.EventDetailResponseDto;
import salute.oneshot.domain.event.dto.response.EventPageResponseDto;
import salute.oneshot.domain.event.dto.service.GetEventsSDto;
import salute.oneshot.domain.event.service.EventService;

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
                page - 1, size, Sort.by("modifiedAt").descending()
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
}
