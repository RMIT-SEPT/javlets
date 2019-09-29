package com.sept.javlets.userauth;


import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
// @CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
	
	@Autowired 
	private UserRepository userRepository;
	
    // Add new user
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    public AccountBean add(HashMap<String, String> loginInfo) {
    	AccountBean user = userRepository.findByUsername(loginInfo.get("email").split("@")[0]);
    	if (user == null) {
	        // Create bean
	       user = new AccountBean(loginInfo.get("id"));
	       String[] names = loginInfo.get("name").split(" ");
	       if(names.length == 2){
	           user.setGivenName(names[0]);
	           user.setFamilyName(names[1]);
	       }
		   
	       user.setEmail(loginInfo.get("email"));
		   user.setUsername(loginInfo.get("email").split("@")[0]);
		   user.setImageUrl(loginInfo.get("imageUrl"));

	       // Finally, saving user
	        userRepository.save(user);
    	}
        return user;
   }
	
    @GetMapping("/userdb")
	public List<AccountBean> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/get/{id}")
	public AccountBean getUser(@PathVariable String id) {
		System.out.println("ID: " + id);
		return userRepository.findByUsername(id);
	}
	
	@DeleteMapping
	public void removeAllUsers() {
		userRepository.deleteAll();
	}
	
	@DeleteMapping(path="/{username}")
	public void removeUser(@PathVariable String username) {
		userRepository.delete(userRepository.findByUsername(username));
	}

    @PostMapping(path="/login")
    public AccountBean login(@RequestBody HashMap<String, String> loginInfo) {
        String[] arrOfStr = loginInfo.get("email").split("@");

        if (validateId(arrOfStr[0])) {
        	AccountBean acc = null;
            if(userRepository.findByUsername(arrOfStr[0])==null){
                acc = add(loginInfo);
                System.out.println("New user registered");
                System.out.println(acc.toString());
			}
			else{
				acc = userRepository.findByUsername(arrOfStr[0]);
			}
			System.out.printf("Log in: %s\n", arrOfStr[0]);
			System.out.println(userRepository.findByUsername(arrOfStr[0]).toString());
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
    
    private boolean validateId(String Id) {
        return Id.matches("(s|e)\\d{7}");
    }

}
