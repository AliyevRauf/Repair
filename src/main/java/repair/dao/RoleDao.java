package repair.dao;

import repair.model.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Rauf on 17/06/2018.
 */
public interface RoleDao {

    // insert new ROle
    boolean createNewRole(Role role);

    List<Role> listRole();

    Role listRoleById(int id);

    boolean editRole(Role role);


}
