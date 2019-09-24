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

    // Add new user, (Not in database)
     void add(HashMap<String, String> loginInfo) {

         // Create bean
        StudentAccountBean user = new StudentAccountBean(loginInfo.get("id"));
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
            if(!userRepository.findById(loginInfo.get("id")).isPresent()){
                add(loginInfo);
                System.out.println("New user registered");
            }

            System.out.printf("Log in: %s\n", arrOfStr[0]);
                return true;
        } else {
            System.out.println("Login attempt: Not valid student email");
            return false;
        }
}

    @GetMapping("/get")
    @ResponseBody
    public StudentAccountBean getInfo(@RequestParam String id) {
        Optional<StudentAccountBean> user = userRepository.findById(id);
        return user.orElse(null);
    }


    // Registered user count
    @GetMapping("/count")
    public long getUserCount() {
         return userRepository.count();
    }

    // DEBUG
    @GetMapping("/userdb")
    public List<StudentAccountBean> getUserDB() {
        return userRepository.findAll();
    }




}
