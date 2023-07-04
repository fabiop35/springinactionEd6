package com.examples.springexsecurity.springinaction.tacos.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/register")
public class RegistrationController {


 private final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private UserRepository userRepo;
    //private PasswordEncoder passwordEncoder;

    /*public RegistrationController(
            UserRepository userRepo, 
            PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }*/

    /*@GetMapping
    public String registerForm() {

    System.out.println("》》》RegistrationController.GET.return registration");
    LOG.trace(">>> TRACE: RegistrationController <<<");
    LOG.debug("<<< RegistrationController.registerForm >>> - DEBUG log");
    LOG.info("<<< RegistrationController.registerForm >>> - INFO log");
    LOG.error("RegistrationController.registerForm >>> - ERROR log");

        return "registration";
    }*/

    @GetMapping("/getUser")
    public String getUser(Model model){
        System.out.println("》》》getUser");
        model.addAttribute("users", 
                userRepo.findAll());
        return "listUser";

    }

    /*@PostMapping
    public String processRegistration(
                 RegistrationForm form) {
    LOG.info("<<< RegistrationController.processRegistration.SAVE >>>");
    userRepo.save(form.toUser(passwordEncoder));
    LOG.info("<<< RegistrationController.forwardTO login PAGE >>>");
    System.out.println("》》》RegistrationController.POST.return login");
      return "redirect:/login";
    }*/

}





