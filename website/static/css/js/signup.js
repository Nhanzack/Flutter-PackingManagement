function validateForm(event) {
    event.preventDefault();

    const mssvInput = document.getElementById('mssv');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');

    const mssvError = document.getElementById('mssvError');
    const emailError = document.getElementById('emailError');
    const phoneError = document.getElementById('phoneError');
    const passwordError = document.getElementById('passwordError');
    const confirmPasswordError = document.getElementById('confirmPasswordError');

    if (!mssvInput || !emailInput || !phoneInput || !passwordInput || !confirmPasswordInput ||
        !mssvError || !emailError || !phoneError || !passwordError || !confirmPasswordError) {
        console.error('One or more form elements not found!');
        return false;
    }

    const mssv = mssvInput.value;
    const email = emailInput.value;
    const phone = phoneInput.value;
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    mssvError.textContent = '';
    emailError.textContent = '';
    phoneError.textContent = '';
    passwordError.textContent = '';
    confirmPasswordError.textContent = '';

    let isValid = true;

    const mssvPattern = /^\d{8}$/;
    if (!mssvPattern.test(mssv)) {
        mssvError.textContent = 'MSSV phải là số và có đúng 8 chữ số!';
        isValid = false;
    }

    if (!email.endsWith('@vhu.edu.vn')) {
        emailError.textContent = 'Email phải có đuôi @vhu.edu.vn!';
        isValid = false;
    }

    const phonePattern = /^\d{10}$/;
    if (!phonePattern.test(phone)) {
        phoneError.textContent = 'Số điện thoại phải có 10 chữ số!';
        isValid = false;
    }

    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    if (!passwordPattern.test(password)) {
        passwordError.textContent = 'Mật khẩu phải có ít nhất 8 ký tự, bao gồm cả chữ và số!';
        isValid = false;
    }

    if (password !== confirmPassword) {
        confirmPasswordError.textContent = 'Mật khẩu xác nhận không khớp!';
        isValid = false;
    }

    if (isValid) {
        const user = { mssv, email, phone, password };
        localStorage.setItem('user', JSON.stringify(user));
        alert('Đăng ký thành công!');
        window.location.href = 'index.html';
    }

    return isValid;
}