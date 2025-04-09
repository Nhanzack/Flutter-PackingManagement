package com.project.backendProjectFinal.components;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class QRCodeGenerator {
    private static final String QR_CODE_DIRECTORY = "./qrcodes/";

    // Phương thức tạo mã QR và lưu file hình ảnh
    public String generateQRCode(String userId) throws WriterException, IOException {
        // Tạo mã QR từ userId
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(userId, BarcodeFormat.QR_CODE, 300, 300);

        // Tạo thư mục nếu chưa tồn tại
        File directory = new File(QR_CODE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Đường dẫn file QR
        String fileName = userId + ".png";
        String filePath = QR_CODE_DIRECTORY + fileName;
        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        // Trả về đường dẫn lưu trữ của mã QR
        return filePath;
    }
}
