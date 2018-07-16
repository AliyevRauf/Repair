package repair.service;

import org.springframework.stereotype.Component;
import repair.model.AjaxRespAutocomplete;
import repair.model.Branch;
import repair.model.OrderResponse;
import repair.model.Users;

import java.util.List;

/**
 * Created by Rauf on 7/11/2018.
 */
@Component
public interface UserService {

    List<AjaxRespAutocomplete> branch(String branchKey);

    Users findByMail(String mail);

    List<String> Actions(int branchId, int userId);

    List<Branch> listBranch();

    boolean insertUser(Users users);

    List<Users> users();

    List<OrderResponse> listorders(Integer userId);

    List<Users> listRoles();

    Users listUserByUserId(Integer userId);

    boolean deleteBranchUsers(Integer id);

    boolean updateBranchUsers(Integer buId,Integer roleId);

    boolean insertBranchUser(Integer branchid, Integer userID, Integer roleId );

}
