package itmo.is.lab1.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceResponseDTO {
   private Long id;
   private String deviceName;
   private String deviceType;
   private String fingerprint;
   private Instant lastSeenAt;
}
