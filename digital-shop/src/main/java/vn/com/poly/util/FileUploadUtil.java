package vn.com.poly.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    // Viết hàm lưu 1 file

    public static void savaFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path path = Paths.get(uploadDir);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e) {
            throw  new IOException("Cont not save image File"+fileName);
        }
    }

}
