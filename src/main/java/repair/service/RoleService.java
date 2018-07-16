package repair.service;

import org.springframework.stereotype.Component;
import repair.model.Role;

import java.util.List;

/**
 * Created by Rauf on 17/06/2018.
 */
@Component
public interface RoleService {
    // insert new ROle
    boolean createNewRole(Role role);
    // list roles
    List<Role> listRole();
    // role by id
    Role listRoleById(int id);
    // edit role
    boolean editRole(Role role);
}
