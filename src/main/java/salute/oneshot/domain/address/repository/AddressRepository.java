package salute.oneshot.domain.address.repository;

import com.amazonaws.services.s3.transfer.Copy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.address.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM Address a WHERE a.userId = :userId)")
    boolean existsByUserId(@Param("userId") Long userId);

    Optional<Address> findByIdAndUserId(Long addressId, Long userId);

    void deleteAllByUserId(Long userId);

    Optional<Address> findByUserIdAndIsDefaultIsTrue(Long userId);

    @Query("SELECT a FROM Address a " +
            "WHERE a.userId = :userId " +
            "AND (:lastAddressId IS NULL OR " +
            "(a.isDefault = true AND :isFirstPage = true) OR " +
            "(a.isDefault = false AND a.id < :lastAddressId)) " +
            "ORDER BY " +
            "   CASE WHEN a.isDefault = true THEN 0 ELSE 1 END, " +
            "   a.modifiedAt DESC " +
            "LIMIT :size")
    List<Address> findAddressByUserId(
            @Param("userId") Long userId,
            @Param("lastAddressId") Long lastAddressId,
            @Param("isFirstPage") boolean isFirstPage,
            @Param("size") int size);
}
