package salute.oneshot.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.dto.service.AddressSDto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
import salute.oneshot.domain.address.dto.service.UpdateAddressSDto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
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
    public AddressResponseDto getAddress(AddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

        return AddressResponseDto.from(address);
    }

    @Transactional
    public AddressResponseDto updateAddress(UpdateAddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

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

        return AddressResponseDto.from(address);
    }

    @Transactional
    public Long deleteAddress(AddressSDto serviceDto) {
        Address address = getAddressByIdAndUserId(
                serviceDto.getAddressId(),
                serviceDto.getUserId());

        addressRepository.delete(address);

        return serviceDto.getAddressId();
    }

    private boolean isFirstAddress(Long userId) {
        return !addressRepository.existsByUserId(userId);
    }

    private Address getAddressByIdAndUserId(Long addressId, Long userId) {
        return addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ADR_NOT_FOUND));
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.ADR_NOT_FOUND));
    }
}
