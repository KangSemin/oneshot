package salute.oneshot.domain.banner.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.banner.dto.response.BannerPageResponseDto;
import salute.oneshot.domain.banner.dto.response.BannerResponseDto;
import salute.oneshot.domain.banner.dto.service.BannerSDto;
import salute.oneshot.domain.banner.dto.service.UpdateBannerSDto;
import salute.oneshot.domain.banner.dto.service.GetBannersSDto;
import salute.oneshot.domain.banner.entity.Banner;
import salute.oneshot.domain.banner.repository.BannerRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.repository.EventRepository;
import salute.oneshot.global.exception.NotFoundException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final EventRepository eventRepository;

    @Transactional
    public BannerResponseDto createBanner(BannerSDto serviceDto) {
        Event event = eventRepository.findById(serviceDto.getEventId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.BANNER_NOT_FOUND));

        Banner banner = Banner.of(
                event,
                serviceDto.getImageUrl(),
                serviceDto.getStartTime(),
                serviceDto.getEndTime());

        bannerRepository.save(banner);

        return BannerResponseDto.from(banner);
    }

    @Transactional
    public BannerResponseDto updateBanner(UpdateBannerSDto serviceDto) {
        try {
            Event event = eventRepository.getReferenceById(serviceDto.getEventId());
            Banner banner = getBannerById(serviceDto.getBannerId());

            banner.updateBanner(event, serviceDto.getImageUrl());
            return BannerResponseDto.from(banner);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException(ErrorCode.EVENT_NOT_FOUND);
        }
    }

    @Transactional
    public void deleteBanner(Long bannerId) {
        if (bannerRepository.deleteBannerById(bannerId) != 1) {
            throw new NotFoundException(ErrorCode.BANNER_NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public BannerPageResponseDto getBanners(GetBannersSDto serviceDto) {
        Page<Banner> banners = bannerRepository.findBanners(
                serviceDto.getStartTime(),
                serviceDto.getEndTime(),
                serviceDto.getPageable());

        Page<BannerResponseDto> bannerPage =
                banners.map(BannerResponseDto::from);

        return BannerPageResponseDto.from(bannerPage);
    }

    @Transactional(readOnly = true)
    public BannerResponseDto getBanner(Long bannerId) {
        Banner banner = getBannerById(bannerId);
        return BannerResponseDto.from(banner);
    }

    private Banner getBannerById(Long bannerId) {
        return bannerRepository.findById(bannerId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.BANNER_NOT_FOUND));
    }
}