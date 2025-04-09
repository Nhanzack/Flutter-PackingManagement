package com.project.backendProjectFinal.controllers;

import com.project.backendProjectFinal.components.QRCodeGenerator;
import com.project.backendProjectFinal.dtos.UserDTO;
import com.project.backendProjectFinal.dtos.UserLoginDTO;
import com.project.backendProjectFinal.models.Users;
import com.project.backendProjectFinal.responses.LoginResponse;
import com.project.backendProjectFinal.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final IUserService userService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final QRCodeGenerator qrCodeGenerator;

    @PostMapping("/register")
    //can we register an "admin" user ?
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        try {
            // Kiểm tra lỗi từ các trường trong UserDTO
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            // Kiểm tra sự khớp của password và retypePassword
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())){
                return ResponseEntity.badRequest().body("Password does not match");
            }

            // Tạo user mới, tự động gán role là 1 (User) và không mã hóa password
            Users user = userService.createUser(userDTO);

            // Tạo mã QR cho người dùng sau khi tạo tài khoản thành công
            String qrCode = qrCodeGenerator.generateQRCode(user.getId().toString());

            // Trả về response với thông tin user và mã QR
            user.setQrCode(qrCode);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        try {
            // Kiểm tra thông tin đăng nhập và sinh token
            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());

            // Xử lý thông báo nếu không có MessageSource
            String message;
            try {
                Locale locale = request.getLocale(); // Lấy locale từ request (thay vì localeResolver)
                message = messageSource.getMessage("user.login.login_successfull", null, locale);
            } catch (NoSuchMessageException ex) {
                message = "Đăng nhập thành công"; // Mặc định nếu không có message key
            }

            // Trả về token trong response
            return ResponseEntity.ok(LoginResponse.builder()
                    .message(message)
                    .token(token)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(e.getMessage())
                    .build());
        }
    }
}
