package demo_ver.demo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo_ver.demo.model.ManageUser;
import demo_ver.demo.service.ManageRoleService;
import demo_ver.demo.service.ManageUserService;

@Controller
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    // @GetMapping("/manageuser")
    // @ResponseBody
    // public List<ManageUser> getAllUsers(){
    // return manageUserService.getAllUsers();
    // }

    @GetMapping("/manageuser")
    public String manageusers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        // Check if the user has the Admin role
        boolean isAdmin = authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_Admin"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("users", ManageUserService.getAllUsers());
        return "ManageUser";
    }

    @GetMapping("/adduser")
    public String showAddUserPage(Model model) {
        model.addAttribute("manageUser", new ManageUser());
        model.addAttribute("roles", ManageRoleService.getAllRoles());
        return "ManageUserAdd";
    }

    @PostMapping("/adduser")
    public String adduser(@ModelAttribute("manageUser") ManageUser manageUser, @RequestParam("role") int roleID,
            Model model) {
        model.addAttribute("roles", ManageRoleService.getAllRoles());
        if (manageUserService.isUsernameExists(manageUser.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "ManageUserAdd";
        }

        if (manageUserService.isEmailExists(manageUser.getEmail())) {
            model.addAttribute("emailExists", true);
            return "ManageUserAdd";
        }

        manageUserService.addUser(manageUser, roleID);

        // model.addAttribute("manageUser", manageUser);

        return "redirect:/manageuser";
    }

    @GetMapping("/deleteuser/{userID}")
    public String deleteUser(@PathVariable("userID") int userID) {
        manageUserService.deleteUser(userID);
        return "redirect:/manageuser";
    }

    @GetMapping("/edituser/{userID}")
    public String showEditUserForm(@PathVariable("userID") int userID, Model model) {
        ManageUser userToEdit = manageUserService.getUserById(userID);
        model.addAttribute("manageUser", userToEdit);
        model.addAttribute("roles", ManageRoleService.getAllRoles());
        return "ManageUserEdit"; //
    }

   // ...

@PostMapping("/updateuser")
public String updateUser(@ModelAttribute("manageUser") ManageUser manageUser, @RequestParam("roleID") int roleID, Model model) {
    model.addAttribute("roles", ManageRoleService.getAllRoles());

    // Check if the username already exists (excluding the current user)
    if (manageUserService.isUsernameExistsExcludingCurrentUser(manageUser.getUsername(), manageUser.getUserID())) {
        model.addAttribute("usernameExists", true);
        return "ManageUserEdit"; // Return to the edit form with an error message
    }

    // Check if the email already exists (excluding the current user)
    if (manageUserService.isEmailExistsExcludingCurrentUser(manageUser.getEmail(), manageUser.getUserID())) {
        model.addAttribute("emailExists", true);
        return "ManageUserEdit"; // Return to the edit form with an error message
    }

    manageUserService.updateUser(manageUser, roleID);
    return "redirect:/manageuser";
}

// ...

    
}
