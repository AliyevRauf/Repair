package repair.service;

import org.springframework.stereotype.Component;
import repair.model.*;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
@Component
public interface BranchService {

    List<AjaxRespAutocomplete> users(String userKey);

    List<AjaxRespAutocomplete> city(String cityKey);

    List<AjaxRespAutocomplete> region(String regionKey);

    boolean insertBranch(Branch branch);

    List<Branch> listBranches();

    List<Users> listUsersByBranchId(Integer id);

    Branch listBranchById(Integer id);

    List<Users> listRoles();

    boolean deleteBranchUsers(Integer id);

    boolean updateBranchUsers(Integer buId,Integer roleId);

    boolean insertBranchUser(Integer id, Integer userID, Integer roleId );

}
