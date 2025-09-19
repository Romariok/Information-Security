package itmo.is.lab1.back.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itmo.is.lab1.back.model.User;
import itmo.is.lab1.back.repository.UserRepository;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;

   @Override
   @Transactional(readOnly = true)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

      return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            new HashSet<>());

   }

   @Transactional(readOnly = true)
   public User findByUsername(String username) {

      return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
   }

   @Transactional(readOnly = true)
   public boolean existsByUsername(String username) {
      return userRepository.existsByUsername(username);
   }

   @Transactional
   public User saveUser(User user) {
      return userRepository.save(user);
   }

}