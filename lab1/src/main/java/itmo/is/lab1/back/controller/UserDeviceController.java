package itmo.is.lab1.back.controller;

import itmo.is.lab1.back.dto.UserDeviceRequestDTO;
import itmo.is.lab1.back.dto.UserDeviceResponseDTO;
import itmo.is.lab1.back.service.UserDeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class UserDeviceController {

   @Autowired
   private UserDeviceService userDeviceService;

   @GetMapping
   public ResponseEntity<List<UserDeviceResponseDTO>> list(Authentication authentication) {
      String username = (String) authentication.getPrincipal();
      return ResponseEntity.ok(userDeviceService.list(username));
   }

   @PostMapping
   public ResponseEntity<UserDeviceResponseDTO> create(Authentication authentication,
         @Valid @RequestBody UserDeviceRequestDTO request) {
      String username = (String) authentication.getPrincipal();
      return ResponseEntity.ok(userDeviceService.create(username, request));
   }

   @PutMapping("/{id}")
   public ResponseEntity<UserDeviceResponseDTO> update(Authentication authentication,
         @PathVariable Long id,
         @Valid @RequestBody UserDeviceRequestDTO request) {
      String username = (String) authentication.getPrincipal();
      return ResponseEntity.ok(userDeviceService.update(username, id, request));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(Authentication authentication, @PathVariable Long id) {
      String username = (String) authentication.getPrincipal();
      userDeviceService.delete(username, id);
      return ResponseEntity.noContent().build();
   }
}
