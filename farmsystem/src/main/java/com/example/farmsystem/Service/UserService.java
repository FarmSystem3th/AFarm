package com.example.farmsystem.Service;

import com.example.farmsystem.Entity.User;
import com.example.farmsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 사용자 등록
    public User registerUser(User user) {
        // 비밀번호 암호화 encode() 스프링 시큐리티 기능
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // 사용자 조회
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    // 로그인 - userId와 비밀번호 확인
    public Optional<User> loginUser(String userId, String password) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 비밀번호 일치 여부 확인 스프링 시큐리티 matches기능 사용
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty(); // 로그인 실패
    }

    // 사용자 정보 수정
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setNickname(userDetails.getNickname());
        user.setAddress(userDetails.getAddress());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
