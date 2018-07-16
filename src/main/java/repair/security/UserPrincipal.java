package repair.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import repair.model.Users;
import repair.service.UserService;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 7/12/2018.
 */
public class UserPrincipal implements UserDetails {

    private List<GrantedAuthority> roles;
    private int branch;
    private Users user;

    protected UserPrincipal(Users user,
                            List<GrantedAuthority> roles,
                            int branchId) {
        this.roles = roles;
        this.branch = branchId;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId(){
        return user.getUserId();
    }

    public int getBranchId(){
        return branch;
    }

}
