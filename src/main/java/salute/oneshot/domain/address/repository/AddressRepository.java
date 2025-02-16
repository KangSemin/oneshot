package salute.oneshot.domain.address.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.address.entity.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM Address a WHERE a.userId = :userId)")
    boolean existsByUserId(@Param("userId") Long userId);

    Page<Address> findAllByUserId(Long id, Pageable pageable);

    Optional<Address> findByIdAndUserId(Long addressId, Long userId);

    void deleteAllByUserId(Long userId);

    Optional<Address> findByUserIdAndIsDefaultIsTrue(Long userId);
}
