package salute.oneshot.domain.event.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventPageResponseDto {

    private final List<EventBriefResponseDto> events;
    private final int currentPage;
    private final int totalPages;
    private final boolean hasNext;

    public static EventPageResponseDto from(
            Page<EventBriefResponseDto> pageResponseDto
    ) {
        return new EventPageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext());
    }
}
