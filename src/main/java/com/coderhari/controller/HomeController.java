package com.coderhari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderhari.dao.UserRepository;
import com.coderhari.entitie.User;
import com.coderhari.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Samart Contect Manager");

        return "home";
    }

    @RequestMapping("/about")
    public String dashboard() {
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register - Samart Contect Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
            @RequestParam(value = "agreement", defaultValue = "false") Boolean agreement, Model model,
            HttpSession session) {
        try {
            if (!agreement) {
                System.out.println("You have not agreed the terms and coditions");
                throw new Exception("You have not agreed the terms and coditions");

            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("Agreement" + agreement);
            System.out.println("USER" + user);

            userRepository.save(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered !!", "alert - success"));
            // session.removeAttribute("message");
            return "redirect:/signup";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something Went Wrong !!" + e.getMessage(), "alert - danger"));
            // session.removeAttribute("message");
            return "redirect:/signup";

        }

    }

    @RequestMapping("/login")
    public String Login(Model model, HttpSession session) {

        model.addAttribute("title", "Login - Samart Contect Manager");
        session.setAttribute("message", new Message("Successfully Login !!", "alert-succes"));

        return "login";
    }

}
