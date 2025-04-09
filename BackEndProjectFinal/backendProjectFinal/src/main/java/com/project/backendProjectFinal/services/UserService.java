package com.project.backendProjectFinal.services;

import com.project.backendProjectFinal.components.JwtTokenUtil;
import com.project.backendProjectFinal.components.QRCodeGenerator;
import com.project.backendProjectFinal.dtos.UserDTO;
import com.project.backendProjectFinal.exceptions.DataNotFoundException;
import com.project.backendProjectFinal.models.Roles;
import com.project.backendProjectFinal.models.Users;
import com.project.backendProjectFinal.repositories.RoleRepository;
import com.project.backendProjectFinal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final QRCodeGenerator qrCodeGenerator;

    @Override
    public Users createUser(UserDTO userDTO) throws Exception {
        String phoneNumber = userDTO.getPhoneNumber();

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        // Lấy role "User" từ bảng Role (role_id = 1)
        Roles role = roleRepository.findById(1L) // Hardcoded role_id = 1 cho User
                .orElseThrow(() -> new DataNotFoundException("Role not found"));

        // Tạo user mới (bỏ qua mã hóa mật khẩu)
        Users newUser = Users.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword()) // Lưu trực tiếp mật khẩu
                .address(userDTO.getAddress())
                .email(userDTO.getEmail())
                .image(userDTO.getImage())
                .roles(role) // Gán role mặc định là "User"
                .build();

        // Lưu user vào database
        Users savedUser = userRepository.save(newUser);

        // Tạo mã QR chứa user_id
        String qrCodePath = qrCodeGenerator.generateQRCode(savedUser.getId().toString());

        // Cập nhật đường dẫn mã QR vào user
        savedUser.setQrCode(qrCodePath);

        // Lưu lại user với mã QR
        return userRepository.save(savedUser);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<Users> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid phone number / password");
        }

        Users existingUser = optionalUser.get();

        // Bỏ qua kiểm tra mật khẩu vì không mã hóa
        if (!existingUser.getPassword().equals(password)) {
            throw new BadCredentialsException("Wrong phone number or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber, password, existingUser.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(existingUser);
    }

}
