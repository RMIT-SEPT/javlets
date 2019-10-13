package com.sept.javlets.connection;


import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        if (userRepository.findById(id).isPresent()) {
            AccountBean requester = userRepository.findById(id).get();

            // Getting non-connection people
            for (AccountBean user : userRepository.findAll()) {

                // Loop through connections, checking if in list.
                if (!user.getId().equals(requester.getId()) &&
                        !requester.getConnections().contains(user.getId()) &&
                        !requester.getConnectSent().contains(user.getId()) &&
                        !requester.getConnectRequest().contains(user.getId())) {

                    returnList.add(user);

                }
            }
        }

        return returnList;
    }

    @GetMapping("/connectSent")
    @ResponseBody
    public List<AccountBean> getConnectSent(@RequestParam String id) {

        List<AccountBean> list = new ArrayList<>();
        if (userRepository.findById(id).isPresent()) {
            AccountBean user = userRepository.findById(id).get();

            for (String userId : user.getConnectSent()) {
                if (!user.getConnections().contains(userId)) {
                    list.add(userRepository.findById(userId).get());
                }
            }
        }
        return list;
    }

    @GetMapping("/connectRequest")
    @ResponseBody
    public List<AccountBean> getConnectRequest(@RequestParam String id) { // People who have sent a connection request to user.

        List<AccountBean> list = new ArrayList<>();
        if (userRepository.findById(id).isPresent()) {
            AccountBean user = userRepository.findById(id).get();

            for (String userId : user.getConnectRequest()) {
                if (!user.getConnections().contains(userId)) {
                    list.add(userRepository.findById(userId).get());
                }
            }
        }
        return list;
    }

    @PostMapping(path = "/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody HashMap<String, String> info) {

        if (userRepository.findById(info.get("senderId")).isPresent() && userRepository.findById(info.get("recipientId")).isPresent()) {
            AccountBean sender = userRepository.findById(info.get("senderId")).get();
            AccountBean recipient = userRepository.findById(info.get("recipientId")).get();

            // If recipient has already added sender (Awaiting response), just add them
            if (recipient.getConnectSent().contains(sender.getId())) {
                System.out.println(info.get("senderId") + " added " + info.get("recipientId"));

                // Make the connection
                sender.addConnection(recipient.getId());
                recipient.addConnection(sender.getId());

                // Cleanup connection partial lists
                sender.removeConnectRequest(recipient.getId());
                sender.removeConnectSent(recipient.getId());

                recipient.removeConnectRequest(sender.getId());
                recipient.removeConnectSent(sender.getId());

            } else { // Else add request
                System.out.println(info.get("senderId") + " wants to connect " + info.get("recipientId"));
                recipient.addConnectRequest(sender.getId());
                sender.addConnectSent(recipient.getId());
            }

            userRepository.save(sender);
            userRepository.save(recipient);
        }
    }

    @GetMapping("/connections")
    @ResponseBody
    public List<AccountBean> getConnections(@RequestParam String id) {
        List<AccountBean> list = new ArrayList<>();
        if (userRepository.findById(id).isPresent()) {
            for (String userId : userRepository.findById(id).get().getConnections()) {
                list.add(userRepository.findById(userId).get());
            }
        }
        return list;
    }
}
