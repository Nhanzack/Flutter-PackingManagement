import 'package:flutter/material.dart';
import 'forgot_password_screen.dart';

class SignupScreen extends StatelessWidget {
  const SignupScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("lib/assets/10_uu_the_01.jpg"), // Ảnh nền
            fit: BoxFit.cover,
          ),
        ),
        child: Center(
          child: Container(
            padding: EdgeInsets.all(20),
            decoration: BoxDecoration(
              color: Colors.black.withOpacity(0.7), // Làm mờ nền
              borderRadius: BorderRadius.circular(10),
            ),
            width: 350,
            child: Column(
              mainAxisSize: MainAxisSize.min,
              
              children: [
                Image.asset("lib/assets/images.png", width: 100),
                SizedBox(height: 10),
                Text("Chào mừng", style: TextStyle(color: Colors.white, fontSize: 22, fontWeight: FontWeight.bold)),
                Text("Đăng ký tài khoản của bạn!", style: TextStyle(color: Colors.white70)),
                
                _buildInputField(Icons.person, "MSSV"),
                _buildInputField(Icons.email, "Email"),
                _buildInputField(Icons.phone, "Phone number"),
                _buildInputField(Icons.lock, "Password", obscureText: true),
                _buildInputField(Icons.lock, "Confirm Password", obscureText: true),

                SizedBox(height: 10),
                ElevatedButton(
                  onPressed: () {},
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.blue.shade900,
                    minimumSize: Size(double.infinity, 50),
                  ),
                  child: Text("Sign Up"),
                ),

                SizedBox(height: 10),
                GestureDetector(
                  onTap: () {
                    Navigator.push(context, MaterialPageRoute(builder: (context) => ForgotPasswordScreen()));
                  },
                  child: Text("Forgot Password?", style: TextStyle(color: Colors.lightBlueAccent)),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildInputField(IconData icon, String hint, {bool obscureText = false}) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: TextField(
        obscureText: obscureText,
        style: TextStyle(color: Colors.white),
        decoration: InputDecoration(
          prefixIcon: Icon(icon, color: Colors.white),
          hintText: hint,
          hintStyle: TextStyle(color: Colors.white70),
          filled: true,
          fillColor: Colors.white.withOpacity(0.2),
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(10), borderSide: BorderSide.none),
        ),
      ),
    );
  }
}
