
document.getElementById("loginBtn").addEventListener("click", function() {
    alert("Chuyển đến trang đăng nhập!");
    window.location.href = "login.html";
});

document.getElementById("signupBtn").addEventListener("click", function() {
    alert("Chuyển đến trang đăng ký!");
    window.location.href = "sign up.html";
});
document.getElementById("signupBtn").addEventListener("click", function() {
    alert("Chuyển đến trang đăng ký!");
    window.location.href = "sign up.html";
});


function showScreen(screenId) {
    document.querySelectorAll('.container').forEach(container => {
        container.classList.add('hidden');
    });
    document.getElementById(screenId).classList.remove('hidden');
}
function togglePassword(id) {
    let input = document.getElementById(id);
    let eyeIcon = input.nextElementSibling;
    if (input.type === "password") {
        input.type = "text";
        eyeIcon.classList.remove("fa-eye");
        eyeIcon.classList.add("fa-eye-slash");
    } else {
        input.type = "password";
        eyeIcon.classList.remove("fa-eye-slash");
        eyeIcon.classList.add("fa-eye");
    }
}
// Đóng modal khi nhấn vào dấu X
document.querySelector(".close").addEventListener("click", function() {
    document.getElementById("forgotPasswordModal").style.display = "none";
});

document.querySelector(".hidden").style.display = "block";


