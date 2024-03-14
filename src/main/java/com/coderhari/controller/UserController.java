package com.coderhari.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coderhari.dao.UserRepository;
import com.coderhari.entitie.Contact;
import com.coderhari.entitie.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userRepository.getUsetByUserName(userName);
        model.addAttribute("user", user);

    }

    @RequestMapping("/index")
    public String about(Model model, Principal principal) {
        model.addAttribute("title", "About - Samart Contect Manager");

        return "normal/user_dashbord";
    }

    @GetMapping("/addContact")
    public String openAddContactForm(Model model) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "normal/addContact";

    }

    // Processing add contact form
    @PostMapping("/processcontact")
    public String processcontact(@ModelAttribute Contact c, @RequestParam("profileImage") MultipartFile file,
            Principal principal, HttpSession session) {
        try {
            String name = principal.getName();
            User user = this.userRepository.getUsetByUserName(name);

            // uploding file
            if (file.isEmpty()) {
                System.out.println("File is Empty");

            } else {
                c.setImage(file.getOriginalFilename());
                File saveFail = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFail.getAbsolutePath() + File.separator + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Img is uploaded");
            }
            c.setUser(user);
            user.getContact().add(c);

            this.userRepository.save(user);
            System.out.println("DATA" + c);
            System.out.println("add to data base");
            session.setAttribute("message", "your contact is added !! Add more ...");

        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            e.printStackTrace();
            session.setAttribute("message", "some went wrong !!");

        }

        return "/normal/addContact";
    }
}
