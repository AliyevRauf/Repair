package repair.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repair.model.Users;
import repair.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String detail)
            throws UsernameNotFoundException {
        String[] detailArray = detail.split(",");
        if (detailArray.length != 2 || detailArray[0].isEmpty()) {
            throw new UsernameNotFoundException("Branch not selected");
        }

        String mail = detailArray[1];
        int branchId = 0;
        try {
            branchId = Integer.parseInt(detailArray[0]);
        } catch (ArithmeticException e) {
            throw new UsernameNotFoundException("Branch is not parsable");
        }

        Users user = userService.findByMail(mail);
        if (user == null) {
            System.out.println("UserModel not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new UserPrincipal(user, getGrantedAuthorities(user.getUserId(), branchId), branchId);
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                true, true, true, true, getGrantedAuthorities(user.getUserId(),branchId));
    }


    private List<GrantedAuthority> getGrantedAuthorities(int userId, int branchId) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> actions = userService.Actions(branchId, userId);
        for (int i = 0; i < actions.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(actions.get(i)));
        }
        return authorities;
    }


}
