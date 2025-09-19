package itmo.is.lab1.back.service;

import itmo.is.lab1.back.dto.UserDeviceRequestDTO;
import itmo.is.lab1.back.dto.UserDeviceResponseDTO;
import itmo.is.lab1.back.model.UserDevice;
import itmo.is.lab1.back.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDeviceService {

   private final UserDeviceRepository userDeviceRepository;

   @Transactional
   public UserDeviceResponseDTO create(String username, UserDeviceRequestDTO request) {
      if (userDeviceRepository.existsByFingerprint(request.getFingerprint())) {
         throw new IllegalArgumentException("Device with this fingerprint already exists");
      }
      UserDevice device = new UserDevice();
      device.setUsername(username);
      device.setDeviceName(request.getDeviceName());
      device.setDeviceType(request.getDeviceType());
      device.setFingerprint(request.getFingerprint());
      device.setLastSeenAt(Instant.now());
      UserDevice saved = userDeviceRepository.save(device);
      return toResponse(saved);
   }

   @Transactional(readOnly = true)
   public List<UserDeviceResponseDTO> list(String username) {
      return userDeviceRepository.findAllByUsername(username)
            .stream().map(this::toResponse)
            .collect(Collectors.toList());
   }

   @Transactional
   public UserDeviceResponseDTO update(String username, Long id, UserDeviceRequestDTO request) {
      UserDevice device = userDeviceRepository.findByIdAndUsername(id, username)
            .orElseThrow(() -> new IllegalArgumentException("Device not found"));
      device.setDeviceName(request.getDeviceName());
      device.setDeviceType(request.getDeviceType());
      device.setLastSeenAt(Instant.now());
      UserDevice saved = userDeviceRepository.save(device);
      return toResponse(saved);
   }

   @Transactional
   public void delete(String username, Long id) {
      UserDevice device = userDeviceRepository.findByIdAndUsername(id, username)
            .orElseThrow(() -> new IllegalArgumentException("Device not found"));
      userDeviceRepository.delete(device);
   }

   private UserDeviceResponseDTO toResponse(UserDevice device) {
      return new UserDeviceResponseDTO(
            device.getId(),
            device.getDeviceName(),
            device.getDeviceType(),
            device.getFingerprint(),
            device.getLastSeenAt());
   }
}
