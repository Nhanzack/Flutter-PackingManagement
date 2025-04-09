// Splash Screen
document.addEventListener('DOMContentLoaded', () => {
    const loginBtn = document.getElementById("loginBtn");
    if (loginBtn) {
        loginBtn.addEventListener("click", function() {
            alert("Chuyển đến trang đăng nhập!");
            window.location.href = "login.html";
        });
    }

    const signupBtn = document.getElementById("signupBtn");
    if (signupBtn) {
        signupBtn.addEventListener("click", function() {
            alert("Chuyển đến trang đăng ký!");
            window.location.href = "sign up.html";
        });
    }

    // Xử lý sự kiện click "Quên mật khẩu"
    const forgotPasswordLink = document.getElementById('forgotPasswordLink');
    if (forgotPasswordLink) {
        forgotPasswordLink.addEventListener('click', (e) => {
            e.preventDefault();
            console.log('Forgot Password link clicked');
            openModal('forgotPasswordModal');
        });
    }

    // Xử lý sự kiện click "Khôi phục tài khoản"
    const recoverAccountLink = document.getElementById('recoverAccountLink');
    if (recoverAccountLink) {
        recoverAccountLink.addEventListener('click', (e) => {
            e.preventDefault();
            console.log('Recover Account link clicked');
            openModal('recoverAccountModal');
        });
    }

    // Xử lý gửi mã OTP (Quên mật khẩu)
    const sendOTPButton = document.getElementById('sendOTP');
    if (sendOTPButton) {
        sendOTPButton.addEventListener('click', () => {
            const phone = document.getElementById('phoneForOTP').value;
            const phonePattern = /^\d{10}$/;
            if (!phonePattern.test(phone)) {
                alert('Số điện thoại phải có 10 chữ số!');
                return;
            }
            alert('Mã OTP đã được gửi đến số điện thoại ' + phone);
            document.querySelector('.otp-section').classList.remove('hidden');
        });
    }

    // Xử lý xác nhận OTP
    const verifyOTPButton = document.getElementById('verifyOTP');
    if (verifyOTPButton) {
        verifyOTPButton.addEventListener('click', () => {
            const otp = document.getElementById('otp').value;
            if (otp.length === 6 && /^\d+$/.test(otp)) {
                document.querySelector('.otp-section').classList.add('hidden');
                document.querySelector('.reset-section').classList.remove('hidden');
            } else {
                alert('Mã OTP không hợp lệ! Vui lòng nhập mã 6 chữ số.');
            }
        });
    }

    // Xử lý đặt lại mật khẩu
    const resetPasswordButton = document.getElementById('resetPassword');
    if (resetPasswordButton) {
        resetPasswordButton.addEventListener('click', () => {
            const newPassword = document.getElementById('newPassword').value;
            const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
            if (!passwordPattern.test(newPassword)) {
                alert('Mật khẩu mới phải có ít nhất 8 ký tự, bao gồm cả chữ và số!');
                return;
            }
            alert('Mật khẩu đã được đặt lại thành công!');
            closeModal('forgotPasswordModal');
        });
    }

    // Xử lý khôi phục tài khoản
    const recoverConfirmButton = document.getElementById('recoverConfirm');
    if (recoverConfirmButton) {
        recoverConfirmButton.addEventListener('click', () => {
            const email = document.getElementById('emailForRecover').value;
            const phone = document.getElementById('phoneForRecover').value;
            const phonePattern = /^\d{10}$/;
            if (!email.endsWith('@vhu.edu.vn')) {
                alert('Email phải có đuôi @vhu.edu.vn!');
                return;
            }
            if (!phonePattern.test(phone)) {
                alert('Số điện thoại phải có 10 chữ số!');
                return;
            }
            alert('Mã OTP đã được gửi đến email và số điện thoại của bạn!');
            closeModal('recoverAccountModal');
        });
    }

    // Xử lý click vào các mục trong menu (cho trang index.html)
    const menuItems = {
        vehicleStatus: 'vehicle-status.html',
        userInfo: 'user-info.html',
        vehicleHistory: 'vehicle-history.html',
        support: 'support.html',
        accountSettings: 'account-settings.html',
        changePassword: 'change-password.html'
    };

    Object.keys(menuItems).forEach(item => {
        const element = document.getElementById(item);
        if (element) {
            element.addEventListener('click', () => {
                window.location.href = menuItems[item];
            });
        }
    });

    // Xử lý đăng xuất (cho trang index.html)
    const logoutButton = document.getElementById('logout');
    if (logoutButton) {
        logoutButton.addEventListener('click', () => {
            if (confirm('Bạn có chắc chắn muốn đăng xuất?')) {
                alert('Đăng xuất thành công!');
                window.location.href = './login.html';
            }
        });
    }

    // Xử lý nút Mã QR (cho trang index.html)
    const qrButton = document.querySelector('.qr-btn');
    if (qrButton) {
        qrButton.addEventListener('click', () => {
            alert('Hiển thị mã QR (chức năng giả lập)!');
        });
    }
});

// Hiển thị/Ẩn modal
function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove('hidden');
    } else {
        console.error('Modal with ID ' + modalId + ' not found!');
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add('hidden');
        if (modalId === 'forgotPasswordModal') {
            document.querySelector('.otp-section').classList.add('hidden');
            document.querySelector('.reset-section').classList.add('hidden');
            document.getElementById('phoneForOTP').value = '';
            document.getElementById('otp').value = '';
            document.getElementById('newPassword').value = '';
        }
        if (modalId === 'recoverAccountModal') {
            document.getElementById('emailForRecover').value = '';
            document.getElementById('phoneForRecover').value = '';
        }
    } else {
        console.error('Modal with ID ' + modalId + ' not found!');
    }
}

// Hiển thị/Ẩn mật khẩu
function togglePassword(id) {
    const input = document.getElementById(id);
    const eyeIcon = input.nextElementSibling;
    if (input && eyeIcon && eyeIcon.classList.contains('toggle-password')) {
        if (input.type === "password") {
            input.type = "text";
            eyeIcon.classList.remove("fa-eye");
            eyeIcon.classList.add("fa-eye-slash");
        } else {
            input.type = "password";
            eyeIcon.classList.remove("fa-eye-slash");
            eyeIcon.classList.add("fa-eye");
        }
    } else {
        console.error(`Input with ID ${id} or eye icon not found!`);
    }
}

// Xử lý đăng nhập (giả lập)
function handleLogin(event) {
    event.preventDefault();

    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');

    // Kiểm tra nếu không tìm thấy input
    if (!usernameInput || !passwordInput) {
        console.error('Không tìm thấy trường nhập tên đăng nhập hoặc mật khẩu!');
        return;
    }

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    // Kiểm tra thông tin đầu vào
    if (!username || !password) {
        showMessage('Vui lòng nhập đầy đủ thông tin!', 'error');
        return;
    }

    // Kiểm tra thông tin đăng nhập với dữ liệu trong localStorage
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.mssv === username && user.password === password) {
        showMessage('Đăng nhập thành công!', 'success');
        // Sau khi đăng nhập thành công, chuyển hướng tới trang chính
        window.location.href = 'website/static/home.html';
    } else {
        showMessage('Tên đăng nhập hoặc mật khẩu không đúng!', 'error');
    }
}

// Hiển thị thông báo
function showMessage(message, type) {
    const messageBox = document.getElementById('messageBox');
    if (!messageBox) {
        const newMessageBox = document.createElement('div');
        newMessageBox.id = 'messageBox';
        newMessageBox.style.position = 'fixed';
        newMessageBox.style.top = '10px';
        newMessageBox.style.left = '50%';
        newMessageBox.style.transform = 'translateX(-50%)';
        newMessageBox.style.padding = '10px 20px';
        newMessageBox.style.fontSize = '16px';
        newMessageBox.style.borderRadius = '5px';
        newMessageBox.style.zIndex = 1000;

        document.body.appendChild(newMessageBox);
    }

    messageBox.innerText = message;
    messageBox.style.backgroundColor = type === 'success' ? '#4CAF50' : '#f44336';
    messageBox.style.color = '#fff';

    // Tự động ẩn thông báo sau 3 giây
    setTimeout(() => {
        messageBox.style.display = 'none';
    }, 3000);
}
