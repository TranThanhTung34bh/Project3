package K23CNT3_TranThanhTung_WebBanDienThoai.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("src/main/resources/static/uploads");

    public String store(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        Files.createDirectories(rootLocation);
        String filename = System.currentTimeMillis() + "_" + Path.of(file.getOriginalFilename()).getFileName();
        Path dest = rootLocation.resolve(filename);
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }
}
