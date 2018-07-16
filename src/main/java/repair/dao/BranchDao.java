package repair.dao;

import repair.model.*;

import java.util.List;

/**
 * Created by User on 6/29/2018.
 */
public interface BranchDao {

    List<AjaxRespAutocomplete> users(String userKey);

    List<AjaxRespAutocomplete> city(String cityKey);

    List<AjaxRespAutocomplete> region(String regionKey);

    Integer insertBranch(Branch branch);

    boolean insertBranchUser(Integer id, Integer userID, Integer roleId );

    List<Branch> listBranches();

    public List<Users> listUsersByBranchId(Integer id);

    Branch listBranchById(Integer id);

    List<Users> listRoles();

    boolean deleteBranchUsers(Integer id);

    boolean updateBranchUsers(Integer buId,Integer roleId);


}
