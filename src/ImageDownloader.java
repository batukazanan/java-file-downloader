import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDownloader {

    public static void main(String[] args) {
        String textFilePath = "C:\\xampp\\htdocs\\training-images.txt";
        String outputFolder = "C:\\xampp\\htdocs\\indirilen_gorseller\\";

        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFilePath));
            String urlString = "https://iasbusinessacademy.com";
            String line;
            while ((line = reader.readLine()) != null) {
                String adres = urlString + line;
                URL url = new URL(adres);
                try (InputStream in = url.openStream()) {
                    String fileName = Paths.get(url.getPath()).getFileName().toString();
                    Path outputPath = Paths.get(outputFolder + fileName);
                    File file = new File(outputPath.toString());
                    Files.copy(in, outputPath);
                    System.out.println("Görüntü indirildi: " + fileName);
                } catch (IOException e) {
                    System.err.println("Hata: " + e.getMessage());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
