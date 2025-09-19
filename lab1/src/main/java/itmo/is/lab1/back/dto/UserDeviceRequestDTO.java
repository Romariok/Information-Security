package itmo.is.lab1.back.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDeviceRequestDTO {

   @NotBlank
   @Size(max = 100)
   private String deviceName;

   @NotBlank
   @Size(max = 50)
   private String deviceType;

   @NotBlank
   @Size(min = 16, max = 128)
   private String fingerprint;
}
