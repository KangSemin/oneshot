package salute.oneshot.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public AddressResponseDto createAddress(CreateAddressSdto serviceDto) {
        User proxyUser = userRepository
                .getReferenceById(serviceDto.getUserId());

        Address address = Address.of(
                serviceDto.getAddressName(),
                serviceDto.getPostcode(),
                serviceDto.getPostAddress(),
                serviceDto.getDetailAddress(),
                serviceDto.getExtraAddress(),
                proxyUser);

        setDefaultFirstAddress(address, serviceDto.getUserId());

        addressRepository.save(address);

        return AddressResponseDto.from(address);
    }

    private void setDefaultFirstAddress(Address address, Long userId) {
        if (!addressRepository.existsByUserId(userId)) {
            address.setDefault();
        }
    }
}
