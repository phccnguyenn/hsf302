package javafx.fe_movie_ticket.service;

import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageValidationService {
    
    // Cache để tránh check lại URLs đã kiểm tra
    private final Map<String, Boolean> urlCache = new HashMap<>();
    
    public boolean isImageUrlValid(String imageUrl) {
        if (imageUrl == null || imageUrl.isEmpty()) {
            return false;
        }
        
        // Check cache first
        if (urlCache.containsKey(imageUrl)) {
            return urlCache.get(imageUrl);
        }
        
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD"); // Chỉ check header, không download ảnh
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            boolean isValid = (responseCode >= 200 && responseCode < 300);
            
            // Cache kết quả
            urlCache.put(imageUrl, isValid);
            
            return isValid;
        } catch (Exception e) {
            // Log error và cache kết quả false
            urlCache.put(imageUrl, false);
            return false;
        }
    }
    
    // Kiểm tra và lấy URL backup nếu URL chính không hoạt động
    public String getValidImageUrl(String primaryUrl, String fallbackUrl) {
        if (isImageUrlValid(primaryUrl)) {
            return primaryUrl;
        }
        
        if (fallbackUrl != null && isImageUrlValid(fallbackUrl)) {
            return fallbackUrl;
        }
        
        // Trả về placeholder image nếu cả 2 đều fail
        return "/uploads/movie/img.png";
    }
    
    // Tạo URLs backup từ TMDb với các size khác nhau
    public Map<String, String> getImageVariants(String tmdbImagePath) {
        Map<String, String> variants = new HashMap<>();
        
        if (tmdbImagePath != null && tmdbImagePath.contains("tmdb.org")) {
            String basePath = tmdbImagePath.substring(tmdbImagePath.lastIndexOf("/") + 1);
            String baseUrl = "https://image.tmdb.org/t/p/";
            
            variants.put("small", baseUrl + "w342/" + basePath);
            variants.put("medium", baseUrl + "w500/" + basePath);
            variants.put("large", baseUrl + "w780/" + basePath);
            variants.put("xlarge", baseUrl + "w1280/" + basePath);
            variants.put("original", baseUrl + "original/" + basePath);
        }
        
        return variants;
    }
    
    // Clear cache (để refresh URLs)
    public void clearCache() {
        urlCache.clear();
    }
    
    // Get cache stats
    public Map<String, Object> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUrls", urlCache.size());
        stats.put("validUrls", urlCache.values().stream().mapToInt(valid -> valid ? 1 : 0).sum());
        stats.put("invalidUrls", urlCache.values().stream().mapToInt(valid -> valid ? 0 : 1).sum());
        return stats;
    }
}