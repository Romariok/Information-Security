package itmo.is.lab1.back.controller;

import itmo.is.lab1.back.dto.AuthRequestDTO;
import itmo.is.lab1.back.dto.AuthResponseDTO;
import itmo.is.lab1.back.dto.RegisterRequestDTO;
import itmo.is.lab1.back.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

   private final AuthService authService;

   @PostMapping("/register")
   public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
      return ResponseEntity.ok(authService.register(request));
   }

   @PostMapping("/login")
   public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) {
      return ResponseEntity.ok(authService.login(request));
   }
}
