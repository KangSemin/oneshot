package salute.oneshot.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.dto.service.GetAddressSDto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
import salute.oneshot.domain.address.dto.service.UpdateAddressSDto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public AddressResponseDto createAddress(CreateAddressSdto serviceDto) {
        Address address = Address.of(
                serviceDto.getAddressName(),
                serviceDto.getPostcode(),
                serviceDto.getPostAddress(),
                serviceDto.getDetailAddress(),
                serviceDto.getExtraAddress(),
                serviceDto.getUserId());

        if (isFirstAddress(serviceDto.getUserId())) {
            address.setDefault();
        }
        addressRepository.save(address);

        return AddressResponseDto.from(address);
    }

    @Transactional(readOnly = true)
    public AddressPageResponseDto getAddresses(GetAddressesSDto serviceDto) {
        Page<Address> addresses = addressRepository.findAllByUserId(
                serviceDto.getUserId(),
                serviceDto.getPageable());

        Page<AddressResponseDto> addressPage =
                addresses.map(AddressResponseDto::from);

        return AddressPageResponseDto.from(addressPage);
    }

    @Transactional(readOnly = true)
    public AddressResponseDto getAddress(GetAddressSDto serviceDto) {
        Address address = addressRepository.findByIdAndUserId(
                        serviceDto.getAddressId(),
                        serviceDto.getUserId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        return AddressResponseDto.from(address);
    }

    @Transactional
    public AddressResponseDto updateAddress(UpdateAddressSDto serviceDto) {
        Address address = addressRepository.findById(serviceDto.getAddressId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        if (!address.getUserId().equals(serviceDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS);
        }

        address.updateAddress(
                serviceDto.getAddressName(),
                serviceDto.getPostcode(),
                serviceDto.getPostAddress(),
                serviceDto.getDetailAddress(),
                serviceDto.getExtraAddress(),
                serviceDto.getUserId());

        return AddressResponseDto.from(address);
    }

    private boolean isFirstAddress(Long userId) {
        return !addressRepository.existsByUserId(userId);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ADR_NOT_FOUND));
    }
}
