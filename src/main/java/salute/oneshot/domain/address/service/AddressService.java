package salute.oneshot.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.dto.response.AddressBriefResponseDto;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.response.AddressDetailResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.dto.service.AddressSDto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
import salute.oneshot.domain.address.dto.service.UpdateAddressSDto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public AddressDetailResponseDto createAddress(CreateAddressSdto serviceDto) {
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

        return AddressDetailResponseDto.from(address);
    }

    @Transactional(readOnly = true)
    public AddressPageResponseDto getAddresses(GetAddressesSDto serviceDto) {
        boolean isFirstPage = serviceDto.getLastAddressId() == null;

        List<Address> addresses = addressRepository.findAddressByUserId(
                serviceDto.getUserId(),
                serviceDto.getLastAddressId(),
                isFirstPage,
                serviceDto.getSize());

        List<AddressBriefResponseDto> addressPage = addresses.stream()
                .map(AddressBriefResponseDto::from)
                .toList();

        boolean hasNext = addresses.size() == serviceDto.getSize();
        Long nextCursor = addresses.isEmpty() ? null : addresses.get(addresses.size() - 1).getId();

        return AddressPageResponseDto.of(addressPage, hasNext, nextCursor);
    }

    @Transactional(readOnly = true)
    public AddressDetailResponseDto getAddress(AddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

        return AddressDetailResponseDto.from(address);
    }

    @Transactional
    public AddressDetailResponseDto updateAddress(UpdateAddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

        if (!serviceDto.isDefault() && address.isDefault()) {
            throw new InvalidException(ErrorCode.DEFAULT_ADDRESS_REQUIRED);
        }

        if (serviceDto.isDefault()) {
            addressRepository.findByUserIdAndIsDefaultIsTrue(serviceDto.getUserId())
                    .ifPresent(Address::unsetDefault);
        }

        address.updateAddress(
                serviceDto.getAddressName(),
                serviceDto.getPostcode(),
                serviceDto.getPostAddress(),
                serviceDto.getDetailAddress(),
                serviceDto.getExtraAddress(),
                serviceDto.getUserId(),
                serviceDto.isDefault());

        return AddressDetailResponseDto.from(address);
    }

    @Transactional
    public Long deleteAddress(AddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

        if (address.isDefault()) {
            throw new InvalidException(ErrorCode.DEFAULT_ADDRESS);
        }

        addressRepository.delete(address);

        return serviceDto.getAddressId();
    }

    private boolean isFirstAddress(Long userId) {
        return !addressRepository.existsByUserId(userId);
    }

    public Address getAddressByIdAndUserId(Long addressId, Long userId) {
        return addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ADR_NOT_FOUND));
    }
}
