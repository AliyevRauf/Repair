package repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repair.dao.UserDao;
import repair.model.AjaxRespAutocomplete;
import repair.model.Branch;
import repair.model.OrderResponse;
import repair.model.Users;

import java.util.List;

/**
 * Created by User on 7/11/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<AjaxRespAutocomplete> branch(String branchKey) {
        return userDao.branch(branchKey);
    }

    @Override
    public Users findByMail(String mail) {
        return userDao.findByMail(mail);
    }

    @Override
    public List<String> Actions(int branchId, int userId) {
        return userDao.Actions(branchId,userId);
    }

    @Override
    public List<Branch> listBranch() {
        return userDao.listBranch();
    }

    @Override
    public boolean insertUser(Users users) {

        Integer userId;

        userId = userDao.insertUser(users);
        if (userId == null) {
            return false;
        }
        List<Branch> branchList = users.getBranches();
        for(Branch branch : branchList){
            userDao.insertBranchUser(branch.getBranchId(),userId,branch.getRoleId());
        }

        return true;
    }

    @Override
    public List<Users> users() {
        return userDao.users();
    }

    @Override
    public List<OrderResponse> listorders(Integer userId) {
        return userDao.listorders(userId);
    }

    @Override
    public List<Users> listRoles() {
        return userDao.listRoles();
    }

    @Override
    public Users listUserByUserId(Integer userId) {
        return userDao.listUserByUserId(userId);
    }

    @Override
    public boolean deleteBranchUsers(Integer id) {
        return userDao.deleteBranchUsers(id);
    }

    @Override
    public boolean updateBranchUsers(Integer buId, Integer roleId) {
        return userDao.updateBranchUsers(buId,roleId);
    }

    @Override
    public boolean insertBranchUser(Integer branchid, Integer userID, Integer roleId) {
        return userDao.insertBranchUser(branchid,userID,roleId);
    }
}
