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

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
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
	
	@GetMapping(path="/{username}")
	public StudentAccountBean getUser(@PathVariable String username) {
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
	
	public boolean validateID(String ID) {
		if(!ID.matches("(s|e)\\d{7}"))
			return false;
		return true;
	}
	
	@PostMapping(path="/login")
    public void login(@RequestBody HashMap<String,String> loginInfo) {
		String[] arrOfStr = loginInfo.get("email").split("@");
		String studentID = null;
		/////////////////////////////////////////////////////////////////////
		// TODO: Create StudentAccountBean object properly with all fields //
		/////////////////////////////////////////////////////////////////////
		if(validateID(arrOfStr[0])) {
			studentID = arrOfStr[0];
			add(studentID);
			System.out.println(studentID);
			
			if(!studentID.equals(null)) {
				System.out.printf("Log in with: %s", studentID);
			}
		} else {
			System.out.println("Incorrect student email/ID");
		}
		
	}
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
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AccountBean add(@RequestParam String username) {
		// TODO: Use user input to determine account type
		AccountBean user = new AccountBean(username);
		return userRepository.save(user);
	}
	
	@GetMapping
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
	
	public boolean validateID(String ID) {
		if(!ID.matches("(s|e)\\d{7}"))
			return false;
		return true;
	}
	
	@PostMapping(path="/login")
    public void login(@RequestBody HashMap<String,String> loginInfo) {
		String[] arrOfStr = loginInfo.get("email").split("@");
		String studentID = null;
		/////////////////////////////////////////////////////////////////////
		// TODO: Create AccountBean object properly with all fields //
		/////////////////////////////////////////////////////////////////////
		if(validateID(arrOfStr[0])) {
			studentID = arrOfStr[0];
			add(studentID);
			System.out.println(studentID);
			
			if(!studentID.equals(null)) {
				System.out.printf("Log in with: %s", studentID);
			}
		} else {
			System.out.println("Incorrect student email/ID");
		}
		
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
