package repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair.dao.BranchDao;
import repair.dao.OrderDao;
import repair.model.*;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchDao branchDao;


    @Override
    public List<AjaxRespAutocomplete> users(String userKey) {
        return branchDao.users(userKey);
    }

    @Override
    public List<AjaxRespAutocomplete> city(String cityKey) {
        return branchDao.city(cityKey);
    }

    @Override
    public List<AjaxRespAutocomplete> region(String regionKey) {
        return branchDao.region(regionKey);
    }

    @Override
    public boolean insertBranch(Branch branch) {
        Integer branchId;

        branchId = branchDao.insertBranch(branch);
            if (branchId == null) {
                return false;
            }

        List<Users> usersList= branch.getUsers();
        branchDao.insertBranchUser(branchId,1,1);
        for(Users users : usersList){
            branchDao.insertBranchUser(branchId,users.getUserId(),users.getRoleId());
        }



        return true;
    }

    @Override
    public List<Branch> listBranches() {
        return branchDao.listBranches();
    }

    @Override
    public List<Users> listUsersByBranchId(Integer id) {
        return branchDao.listUsersByBranchId(id);
    }

    @Override
    public Branch listBranchById(Integer id) {
        return branchDao.listBranchById(id);
    }

    @Override
    public List<Users> listRoles() {
        return branchDao.listRoles();
    }

    @Override
    public boolean deleteBranchUsers(Integer id) {
        return branchDao.deleteBranchUsers(id);
    }

    @Override
    public boolean updateBranchUsers(Integer buId, Integer roleId) {
        return branchDao.updateBranchUsers(buId,roleId);
    }

    @Override
    public boolean insertBranchUser(Integer id, Integer userID, Integer roleId) {
        return branchDao.insertBranchUser(id,userID,roleId);
    }
}
