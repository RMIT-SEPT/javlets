package com.sept.javlets.userauth;


import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
	
	@Autowired 
	private UserRepository userRepository;
	
    // Add new user
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    void add(HashMap<String, String> loginInfo) {

        // Create bean
       AccountBean user = new AccountBean(loginInfo.get("id"));
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
	
    @GetMapping("/userdb")
	public List<AccountBean> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/{username}")
	public AccountBean getUser(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}
	
	@DeleteMapping
	public void removeAllUsers() {
		userRepository.deleteAll();
	}
	
	@DeleteMapping(path="/{username}")
	public void removeUser(@PathVariable String username) {
		userRepository.delete(userRepository.findByUsername(username));
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

    // Registered user count
    @GetMapping("/count")
    public long getUserCount() {
         return userRepository.count();
    }
    
    private boolean validateId(String Id) {
        return Id.matches("(s|e)\\d{7}");
    }

    @GetMapping(path="/get/{username}")
    public void addConnection(@PathVariable String username, @RequestBody HashMap<String, String> newConnectionInfo){
    	
    	AccountBean currentUser = (userRepository.findByUsername(username));
    	
    	AccountBean toConnect = new AccountBean(newConnectionInfo.get("username"));
		
		for(AccountBean accounts : currentUser.getConnections()) {			
			if(!accounts.getUsername().equals(newConnectionInfo.get("username"))){
				currentUser.addConnection(toConnect);
				System.out.println("New connection added");
			}
		}
    }

}
