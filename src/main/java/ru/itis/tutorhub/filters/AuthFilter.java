package ru.itis.tutorhub.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.services.auth.AuthService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    private final List<String> protectedPaths;
    private AuthService authService;

    public AuthFilter() {
        this.protectedPaths = List.of("/profile", "/applications", "/tutors");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.authService = (AuthService) context.getAttribute("authService");
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        if (!protectedPaths.contains(request.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }

        String sessionToken = extractSessionToken(request.getCookies());
        if (sessionToken == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Optional<User> user = authService.getUserBySession(sessionToken);
        if (user.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("user", user.get());
        chain.doFilter(request, response);
    }

    private String extractSessionToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}