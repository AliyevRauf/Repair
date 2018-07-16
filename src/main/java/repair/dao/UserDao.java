package repair.dao;

import repair.model.AjaxRespAutocomplete;
import repair.model.Branch;
import repair.model.OrderResponse;
import repair.model.Users;

import java.util.List;

/**
 * Created by User on 7/11/2018.
 */
public interface UserDao {

    List<AjaxRespAutocomplete> branch(String branchKey);

    Users findByMail(String mail);

    List<String> Actions(int branchId, int userId);

    List<Branch> listBranch();

    Integer insertUser(Users users);

    boolean insertBranchUser(Integer branchid, Integer userID, Integer roleId );

    List<Users> users();

    List<OrderResponse> listorders(Integer userId);

    List<Users> listRoles();

    Users listUserByUserId(Integer userId);

    boolean deleteBranchUsers(Integer id);

    boolean updateBranchUsers(Integer buId,Integer roleId);

}
