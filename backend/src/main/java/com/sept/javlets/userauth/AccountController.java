package com.sept.javlets.userauth;


import com.sept.javlets.chat.MessageBean;
import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping(path="/get/{username}")
    public void addConnection(@PathVariable String username, @RequestBody HashMap<String, String> newConnectionInfo){
    	
    	StudentAccountBean currentUser = (userRepository.findByUsername(username));
    	
    	StudentAccountBean toConnect = new StudentAccountBean(newConnectionInfo.get("username"));
		
		for(StudentAccountBean accounts : currentUser.getConnections()) {			
			if(!accounts.getUsername().equals(newConnectionInfo.get("username"))){
				currentUser.addConnection(toConnect);
				System.out.println("New connection added");
			}
		}
    }
    
//    @MessageMapping("/message")
//    public void message(MessageBean mBean) {
//        String datetime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()).toString();
//
//        userRepository.findById(mBean.getSenderId()).ifPresent(mBean::setSender);
//        userRepository.findById(mBean.getRecipientId()).ifPresent(mBean::setRecipient);
//        mBean.setDateTime(datetime);
//        messageRepository.save(mBean);
//
//
//        System.out.println("MESSAGE RECEIVED (" + datetime +"): " + mBean.getSender().getUsername() + " sent \"" + mBean.getMsg() + "\" to " + mBean.getRecipient());
//        this.template.convertAndSend("/chat", mBean);
//    }



}
