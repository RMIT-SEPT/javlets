package com.sept.javlets.userauth;


import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    // Add new user
     void add(HashMap<String, String> loginInfo) {
        StudentAccountBean user = new StudentAccountBean(loginInfo.get("Id"));
        String[] names = loginInfo.get("name").split(" ");
        if(names.length == 2){
            user.setGivenName(names[0]);
            user.setFamilyName(names[1]);
        }

        // Username is gotten from email
        String email = loginInfo.get("email");

        user.setEmail(email);
        user.setUsername(loginInfo.get("email").split("@")[0]);

        // Finally, saving user
         userRepository.save(user);
    }



    private boolean validateId(String Id) {
        return Id.matches("(s|e)\\d{7}");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public boolean login(@RequestBody HashMap<String, String> loginInfo) {
        String[] arrOfStr = loginInfo.get("email").split("@");

        if (validateId(arrOfStr[0])) {
                add(loginInfo);

                System.out.printf("Log in with: %s\n", arrOfStr[0]);
                return true;
        } else {
            System.out.println("Incorrect student email/Id");
            return false;
        }
}

    @GetMapping("/get")
    @ResponseBody
    public StudentAccountBean getInfo(@RequestParam String Id) {
        System.out.println(userRepository.findById(Id).isPresent());
        return null;
    }



}
