package salute.oneshot.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.address.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM Address a WHERE a.user.id = :userId)")
    boolean existsByUserId(@Param("userId") Long userId);
}
