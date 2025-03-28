import 'package:flutter/material.dart';
import 'signup.dart';

void main() {
  runApp(MyApp()); // Đây là nơi xảy ra lỗi nếu MyApp chưa được định nghĩa
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Parking App',
      theme: ThemeData(primarySwatch: Colors.blue),
      home:SignupScreen(), // Mở app với màn hình đăng ký
    );
  } 
}