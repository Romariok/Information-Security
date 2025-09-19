package itmo.is.lab1.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "user_devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDevice {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "username", nullable = false)
   private String username;

   @Column(name = "device_name", nullable = false)
   private String deviceName;

   @Column(name = "device_type", nullable = false)
   private String deviceType;

   @Column(name = "fingerprint", nullable = false, unique = true)
   private String fingerprint;

   @Column(name = "last_seen_at", nullable = false)
   private Instant lastSeenAt;

}
