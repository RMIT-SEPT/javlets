package com.sept.javlets.userauth;


import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    // Add user
    public AccountBean add(HashMap<String, String> loginInfo) {
        String id = loginInfo.get("email").split("@")[0];

        AccountBean user = new AccountBean(id);


        String[] names = loginInfo.get("name").split(" ");
        if (names.length == 2) {
            user.setGivenName(names[0]);
            user.setFamilyName(names[1]);
        }

        user.setEmail(loginInfo.get("email"));
        user.setImageUrl(loginInfo.get("imageUrl"));

        // Finally, saving user
        userRepository.save(user);
        return user;
    }

    @GetMapping("/userdb")
    public List<AccountBean> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping
    public void removeAllUsers() {
        userRepository.deleteAll();
    }

    @GetMapping("/get")
    @ResponseBody
    public AccountBean getInfo(@RequestParam String id) {
        Optional<AccountBean> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @PostMapping(path = "/login")
    public AccountBean login(@RequestBody HashMap<String, String> loginInfo) {
        String[] arrOfStr = loginInfo.get("email").split("@");

        if (validateId(arrOfStr[0])) {
            AccountBean acc = null;
            if (!userRepository.findById(arrOfStr[0]).isPresent()) {
                acc = add(loginInfo);
                System.out.println("New user registered");
                System.out.println(acc.toString());
            } else {
                acc = userRepository.findById(arrOfStr[0]).get();
            }


            // Logged in
            System.out.printf("Log in: %s\n", arrOfStr[0]);
            return acc;
        } else {
            System.out.println("Login attempt: Not valid student email");
            return null;
        }
    }

    // Registered user count
    @GetMapping("/count")
    public long getUserCount() {
        return userRepository.count();
    }

    private boolean validateId(String id) {
        return id.matches("(s|e)\\d{7}");
    }

}
