// Xử lý click vào các tab
document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
        tab.classList.add('active');

        if (tab.textContent === 'Trường VHU') {
            window.location.href = 'https://portal.vhu.edu.vn/login';
        } else if (tab.textContent === 'Thoát') {
            if (confirm('Bạn có chắc chắn muốn thoát?')) {
                alert('Thoát thành công!');
                window.location.href = './login.html';
            } else {
                document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
                document.querySelector('.tab:first-child').classList.add('active');
            }
        }
    });
});

// Xử lý click vào các mục trong menu
document.getElementById('vehicleStatus').addEventListener('click', () => {
    window.location.href = 'mainmenu/statusxe.html';
});

document.getElementById('userInfo').addEventListener('click', () => {
    window.location.href = 'mainmenu/menu2.html';
});

document.getElementById('vehicleHistory').addEventListener('click', () => {
    window.location.href = 'mainmenu/menu3.html';
});

document.getElementById('support').addEventListener('click', () => {
    window.location.href = 'settingmenu/menu4.html';
});

document.getElementById('accountSettings').addEventListener('click', () => {
    window.location.href = 'settingmenu/menu6.html';
});

document.getElementById('changePassword').addEventListener('click', () => {
    window.location.href = 'settingmenu/menu7.html';
});

// Xử lý đăng xuất
document.getElementById('logout').addEventListener('click', () => {
    if (confirm('Bạn có chắc chắn muốn đăng xuất?')) {
        alert('Đăng xuất thành công!');
        window.location.href = './login.html';
    }
});

// Xử lý nút Mã QR
document.querySelector('.qr-btn').addEventListener('click', () => {
    alert('Hiển thị mã QR (chức năng giả lập)!');
});

setInterval(() => {
    // Gọi API để cập nhật trạng thái mới nhất của xe
    fetch("/api/status-xe")
      .then(res => res.json())
      .then(data => {
          // cập nhật giao diện
          document.querySelector(".status-value").textContent = data.trangThai;
      });
}, 10000); // mỗi 10 giây cập nhật
