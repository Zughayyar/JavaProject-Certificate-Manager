package com.axsosacademy.javaprojectcertificatemanager.services;
import com.axsosacademy.javaprojectcertificatemanager.models.Approval;
import com.axsosacademy.javaprojectcertificatemanager.models.LoginUser;
import com.axsosacademy.javaprojectcertificatemanager.models.Role;
import com.axsosacademy.javaprojectcertificatemanager.models.User;
import com.axsosacademy.javaprojectcertificatemanager.repositories.ApprovalRepository;
import com.axsosacademy.javaprojectcertificatemanager.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApprovalRepository approvalRepository;
    public UserService(UserRepository userRepository, ApprovalRepository approvalRepository) {
        this.userRepository = userRepository;
        this.approvalRepository = approvalRepository;
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add user
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Register Method:
    public void registerUser(User newUser, BindingResult bindingResult) {
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());

        // Check if email is already taken
        if (potentialUser.isPresent()) {
            bindingResult.rejectValue("email", "email.exists", "Email already in use!");
        }

        // Check if passwords match
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "Matches", "The Confirm Password must match Password!");
        }


        // Hash and set password, save user to database
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
    }

    // Login User:
    public User loginUser(LoginUser newLoginUser, BindingResult bindingResult) {
        // Find user in the DB by email
        Optional<User> potentialUser = userRepository.findByEmail(newLoginUser.getEmail());
        if (potentialUser.isEmpty()) {
            bindingResult.rejectValue("email", "NotFound", "Email not found!");
            return null;
        }

        // Get the user object
        User user = potentialUser.get();

        // Reject if BCrypt password match fails
        if (!BCrypt.checkpw(newLoginUser.getPassword(), user.getPassword())) {
            bindingResult.rejectValue("password", "Invalid", "Invalid Password!");
        }

        // Return null if result has errors
        if (bindingResult.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Save or update a teacher
    public void save(User user) {
        userRepository.save(user);  // Make sure this is saving the updated user in the database
    }
    
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            List<Role> roles = user.getRoles();
            roles.clear();
            userRepository.save(user);
            List<Approval> approvals = user.getApprovalList();
            for (Approval approval : approvals) {
                approval.setUser(null);
                approvalRepository.save(approval);
            }
            userRepository.deleteById(id);
        }

    }

    // Get All Users With Roles
    public List<User> getAllUsersWithRoles() {
        return userRepository.joinUsersAndRoles();
    }

    // get Users and Roles Details
    public List<Object[]> getUsersAndRolesDetails() {
        return userRepository.joinUsersAndRolesWithDetails();
    }
}
