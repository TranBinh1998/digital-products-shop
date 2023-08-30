package vn.com.poly.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionService {
  void sendSession(HttpServletRequest request);
}
