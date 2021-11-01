package com.example.studentapp.controller.error;

import lombok.NonNull;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String error(@NonNull HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = Integer.valueOf(status.toString());
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("errorMessage", "404, Page not found");
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("errorMessage", "500, Internal Server Error");
        } else {
            model.addAttribute("errorMessage", statusCode);
        }
        return "error";
    }
}
