package com.meals.meals_app.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.Filter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterConfig;

import java.io.IOException;

@Component
public class FirebaseAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Firebase Filter Called");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        if(path.startsWith("/api/public/")){
            chain.doFilter(request, response);
            return;
        }
        String token = getBearerToken(httpRequest);
        System.out.println("token: fetched"+ token);

        if (token != null) {
            System.out.println("token isn't null: "+ token);
            try {
                System.out.println("line 31");
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                System.out.println("decoded token:" + decodedToken);
                String userId = decodedToken.getUid();
                System.out.println("userid:" + userId);
                request.setAttribute("userId", userId);

                FirebaseAuthenticationToken firebaseAuthenticationToken = new FirebaseAuthenticationToken(userId);
                SecurityContextHolder.getContext().setAuthentication(firebaseAuthenticationToken);

            } catch (Exception e) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        System.out.print("bearer Token:" + bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            System.out.println("bearer not null");
            System.out.println("bearer substring:" + bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
