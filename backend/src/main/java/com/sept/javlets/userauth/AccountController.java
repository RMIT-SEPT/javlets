package com.sept.javlets.userauth;


import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://javlet.social:80")
@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public StudentAccountBean add(@RequestParam String username) {
        StudentAccountBean user = new StudentAccountBean(username);
        return userRepository.save(user);
    }

    @GetMapping
    public List<StudentAccountBean> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{username}")
    public StudentAccountBean getUser(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @DeleteMapping
    public void removeAllUsers() {
        userRepository.deleteAll();
    }

    @DeleteMapping(path = "/{username}")
    public void removeUser(@PathVariable String username) {
        userRepository.delete(userRepository.findByUsername(username));
    }

    public boolean validateID(String ID) {
        return ID.matches("(s|e)\\d{7}");
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody HashMap<String, String> loginInfo) {
        String[] arrOfStr = loginInfo.get("email").split("@");
        String studentID = null;
        /////////////////////////////////////////////////////////////////////
        // TODO: Create StudentAccountBean object properly with all fields //
        /////////////////////////////////////////////////////////////////////
        if (validateID(arrOfStr[0])) {
            studentID = arrOfStr[0];
            add(studentID);
            System.out.println(studentID);

            if (!studentID.equals(null)) {
                System.out.printf("Log in with: %s", studentID);
            }
        } else {
            System.out.println("Incorrect student email/ID");
        }

    }
}
