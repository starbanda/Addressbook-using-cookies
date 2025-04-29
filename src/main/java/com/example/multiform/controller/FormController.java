package com.example.multiform.controller;

import com.example.multiform.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FormController {

    @GetMapping("/")
    public String page1() {
        return "page1";
    }

    @PostMapping("/processPage1")
    public String processPage1(
            @RequestParam String firstName,
            @RequestParam String lastName,
            HttpServletResponse response) {

        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);

        firstNameCookie.setMaxAge(24 * 60 * 60);
        lastNameCookie.setMaxAge(24 * 60 * 60);

        response.addCookie(firstNameCookie);
        response.addCookie(lastNameCookie);

        return "redirect:/page2";
    }

    @GetMapping("/page2")
    public String page2(
            @CookieValue(value = "firstName", defaultValue = "") String firstName,
            @CookieValue(value = "lastName", defaultValue = "") String lastName,
            Model model) {

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        return "page2";
    }

    @PostMapping("/processPage2")
    public String processPage2(
            @RequestParam String email,
            @RequestParam String phone,
            HttpServletResponse response) {

        Cookie emailCookie = new Cookie("email", email);
        Cookie phoneCookie = new Cookie("phone", phone);

        emailCookie.setMaxAge(24 * 60 * 60);
        phoneCookie.setMaxAge(24 * 60 * 60);

        response.addCookie(emailCookie);
        response.addCookie(phoneCookie);

        return "redirect:/page3";
    }

    @GetMapping("/page3")
    public String page3(
            @CookieValue(value = "firstName", defaultValue = "") String firstName,
            @CookieValue(value = "lastName", defaultValue = "") String lastName,
            @CookieValue(value = "email", defaultValue = "") String email,
            @CookieValue(value = "phone", defaultValue = "") String phone,
            Model model) {

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        return "page3";
    }

    @PostMapping("/processPage3")
    public String processPage3(
            @RequestParam String city,
            @RequestParam String country,
            HttpServletResponse response) {

        Cookie cityCookie = new Cookie("city", city);
        Cookie countryCookie = new Cookie("country", country);

        cityCookie.setMaxAge(24 * 60 * 60);
        countryCookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cityCookie);
        response.addCookie(countryCookie);

        return "redirect:/summary";
    }

    @GetMapping("/summary")
    public String summary(
            @CookieValue(value = "firstName", defaultValue = "") String firstName,
            @CookieValue(value = "lastName", defaultValue = "") String lastName,
            @CookieValue(value = "email", defaultValue = "") String email,
            @CookieValue(value = "phone", defaultValue = "") String phone,
            @CookieValue(value = "city", defaultValue = "") String city,
            @CookieValue(value = "country", defaultValue = "") String country,
            Model model) {

        UserData userData = new UserData();
        userData.setFirstName(firstName);
        userData.setLastName(lastName);
        userData.setEmail(email);
        userData.setPhone(phone);
        userData.setCity(city);
        userData.setCountry(country);

        model.addAttribute("userData", userData);
        return "summary";
    }
}