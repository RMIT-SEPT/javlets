package com.sept.javlets.connection;


import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/nonconnection")
    @ResponseBody
    public List<AccountBean> getNonConnections(@RequestParam String id) {
        List<AccountBean> returnList = new ArrayList<>();

        if(userRepository.findById(id).isPresent()){
            for(AccountBean user : userRepository.findAll()){
            if(userRepository.findById(id).isPresent()){
                if(!user.getId().equals(userRepository.findById(id).get().getId())){
                    returnList.add(user);
                }
            } }
        }
        return returnList;
    }

    @GetMapping("/connection")
    @ResponseBody
    public List<AccountBean> getConnections(@RequestParam String id) {
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get().getConnections();
        }
        return null;
    }

}
