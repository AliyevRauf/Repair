package repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair.dao.RoleDao;
import repair.model.Role;

import java.util.List;

/**
 * Created by Rauf on 17/06/2018.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public boolean createNewRole(Role role) {

        return roleDao.createNewRole(role);
    }

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public Role listRoleById(int id) {
        return roleDao.listRoleById(id);
    }

    @Override
    public boolean editRole(Role role) {
        return roleDao.editRole(role);
    }


}
