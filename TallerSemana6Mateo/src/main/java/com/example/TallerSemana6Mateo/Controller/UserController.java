/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TallerSemana6Mateo.Controller;

/**
 *
 * @author Usuario
 */
import com.example.TallerSemana6Mateo.Model.User;
import com.example.TallerSemana6Mateo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("errorMessage", "");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Las contraseñas no coinciden.");
            return "register";
        }
        // Guardar el usuario en la base de datos
        userRepository.save(user);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Página principal después del registro
    }
}