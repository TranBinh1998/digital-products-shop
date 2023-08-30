package vn.com.poly.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import vn.com.poly.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
    @Override
    public void sendSession(HttpServletRequest request) {
        StringBuffer previousUrl = request.getRequestURL();
        String query = request.getQueryString();
        String fullUrl = previousUrl.append("?").append(query).toString();
        HttpSession session = request.getSession();
        session.setAttribute("previousUrl", fullUrl);
    }
}
