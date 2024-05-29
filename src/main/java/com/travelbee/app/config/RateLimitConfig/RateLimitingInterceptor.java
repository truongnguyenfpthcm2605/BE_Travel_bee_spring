package com.travelbee.app.config.RateLimitConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private final Map<String, Long> requestCounts = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 1000000000;
    private static final long THRESHOLD_TIME_MILLIS = 24 * 60 * 60 * 1000;

    @Override
    public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ipAddress = request.getRemoteAddr();
        // Lây thời gian hiên tại
        long currentTime = System.currentTimeMillis();
        // lấy số lượng http tu ip
        long count = requestCounts.getOrDefault(ipAddress, 0L);
        // lấy thời gian cuối cùng
        long lastRequestTime = requestCounts.getOrDefault(ipAddress + "_time", 0L);


        if (currentTime - lastRequestTime >= THRESHOLD_TIME_MILLIS) {
            requestCounts.put(ipAddress, 1L);
            requestCounts.put(ipAddress + "_time", currentTime);
        } else {
            if (count >= MAX_REQUESTS) {
                response.setStatus(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);
                response.getWriter().write("Too many request in day, please access again!");
                response.getWriter().flush();
                return false;
            } else {
                requestCounts.put(ipAddress, count + 1);
            }
        }

        return true;
    }
}

