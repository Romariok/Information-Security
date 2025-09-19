package itmo.is.lab1.back.repository;

import itmo.is.lab1.back.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
   List<UserDevice> findAllByUsername(String username);

   Optional<UserDevice> findByIdAndUsername(Long id, String username);

   boolean existsByFingerprint(String fingerprint);
}
