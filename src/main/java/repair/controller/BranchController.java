package repair.controller;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repair.dao.BranchDao;
import repair.model.AjaxRespAutocomplete;
import repair.model.Branch;
import repair.model.ResponseModel;
import repair.model.Users;
import repair.security.UserPrincipal;
import repair.service.BranchService;
import repair.util.Constant;
import repair.util.JspView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rauf on 18/06/2018.
 */
@Controller
public class BranchController {

    @Autowired
    BranchService branchService;

    @Autowired
    BranchDao branchDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model, UsernamePasswordAuthenticationToken principal) throws Exception {

        UserPrincipal userPrincipal = (UserPrincipal) principal.getPrincipal();
        model.addAttribute("username", userPrincipal.getUsername());
        List<Branch> listBranch = branchService.listBranches();

        model.addAttribute("list", listBranch);

        return new ModelAndView(JspView.INDEX, model.asMap());
    }

    @RequestMapping(value = "/edit_branch/{branchId}", method = RequestMethod.GET)
    public ModelAndView getEditBranch(@PathVariable int branchId,
                                      UsernamePasswordAuthenticationToken principal,
                                      Model model) throws Exception {

        Branch branch = branchService.listBranchById(branchId);
        List<Users> roleList = branchService.listRoles();

        model.addAttribute("branch", branch);
        model.addAttribute("roleList", roleList);


        return new ModelAndView(JspView.EDITBRANCH, model.asMap());
    }


    @RequestMapping(value = "/view_users/{branchId}", method = RequestMethod.GET)
    public ModelAndView getViewUsers(@PathVariable int branchId,
                                     UsernamePasswordAuthenticationToken principal,
                                     Model model) throws Exception {

        List<Users> usersList = branchService.listUsersByBranchId(branchId);

        model.addAttribute("userList", usersList);


        return new ModelAndView(JspView.VIEWUSERS, model.asMap());
    }


    @PreAuthorize("hasAuthority('create_branch')")
    @RequestMapping(value = "/add_branch", method = RequestMethod.GET)
    public ModelAndView getAddBranch(UsernamePasswordAuthenticationToken principal
                                      ,Model model) throws Exception {

        return new ModelAndView(JspView.ADDBRANCH, model.asMap());
    }

    @PreAuthorize("hasAuthority('create_branch')")
    @RequestMapping(value = "/add_branch", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseModel postAddBranch(
            @RequestParam("users") String users,
            @RequestParam("roles") String roles,
            @RequestParam("branchNameId") String branchName,
            @RequestParam("emailId") String email,
            @RequestParam("city_hidden_Id") int city,
            @RequestParam("region_hidden_Id") int region,
            @RequestParam("addressId") String address
    ) throws Exception {
        System.out.println(users);
        System.out.println(roles);
        String[] usersMass = users.split("--");
        String[] rolesMass = roles.split("--");
        List<Users> usersList = new ArrayList();
        if (usersMass.length == rolesMass.length)
            for (int i = 0; i < usersMass.length && !usersMass[i].isEmpty(); i++) {
                Users u = new Users();
                int userID = Integer.parseInt(usersMass[i]);
                int roleID = Integer.parseInt(rolesMass[i]);
                u.setUserId(userID);
                u.setRoleId(roleID);
                usersList.add(u);
            }

        Branch branch = new Branch();
        branch.setName(branchName);
        branch.setEmail(email);
        branch.setCityId(city);
        branch.setRegionId(region);
        branch.setAddress(address);
        branch.setUsers(usersList);

        boolean result = branchService.insertBranch(branch);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(Constant.OKAY_CODE);
        responseModel.setTitle(Constant.OKAY_TITLE);
        return responseModel;
    }

    @RequestMapping(value = "/edit_branch", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseModel postEditBranch(
            @RequestParam("branchId") int branchID,
            @RequestParam("buid") String buid,
            @RequestParam("users") String users,
            @RequestParam("roles") String roles,
            @RequestParam("deleted") String deleted,
            @RequestParam("branchNameId") String branchName,
            @RequestParam("emailId") String email,
            @RequestParam("city_hidden_Id") int city,
            @RequestParam("region_hidden_Id") int region,
            @RequestParam("addressId") String address
    ) throws Exception {

        System.out.println(roles);
        if (deleted != "") {
            String[] massDeleted = deleted.split("aa");
            for (int i = 0; i < massDeleted.length; i++) {
                boolean delete = branchService.deleteBranchUsers(Integer.parseInt(massDeleted[i]));
            }
        }
        String[] massRole = roles.split("aa");
        String[] massUsers = users.split("aa");
        if (buid != "") {
            String[] massBu = buid.split("aa");

            if (massUsers.length == massRole.length) {
                for (int i = 0; i < massBu.length; i++) {
                    if (Integer.parseInt(massBu[i]) != -1) {
                        boolean update = branchService.updateBranchUsers(Integer.parseInt(massBu[i]), Integer.parseInt(massRole[i]));
                    } else if (Integer.parseInt(massBu[i]) == -1) {
                        boolean insert = branchService.insertBranchUser(branchID, Integer.parseInt(massUsers[i]), Integer.parseInt(massRole[i]));
                    }

                }

            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(Constant.OKAY_CODE);
        responseModel.setTitle(Constant.OKAY_TITLE);
        return responseModel;
    }

    @RequestMapping(value = "/get_users", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<AjaxRespAutocomplete> getUsers(@RequestParam("userId") String usersKey) throws Exception {

        List<AjaxRespAutocomplete> ajaxResponse = new ArrayList<>();
        String response = null;
        try {


            List<AjaxRespAutocomplete> listUsers = branchService.users(usersKey.toUpperCase());

            if (listUsers.size() > 0) {
                for (AjaxRespAutocomplete user : listUsers) {
                    AjaxRespAutocomplete ajaxRespUsers = new AjaxRespAutocomplete();
                    ajaxRespUsers.setValue(user.getValue());
                    ajaxRespUsers.setLabel(user.getLabel());
                    ajaxResponse.add(ajaxRespUsers);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/get_city", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<AjaxRespAutocomplete> getCity(@RequestParam("cityId") String cityKey) throws Exception {

        List<AjaxRespAutocomplete> ajaxResponse = new ArrayList<>();
        String response = null;
        try {


            List<AjaxRespAutocomplete> listCity = branchService.city(cityKey.toUpperCase());

            if (listCity.size() > 0) {
                for (AjaxRespAutocomplete city : listCity) {
                    AjaxRespAutocomplete ajaxRespUsers = new AjaxRespAutocomplete();
                    ajaxRespUsers.setValue(city.getValue());
                    ajaxRespUsers.setLabel(city.getLabel());
                    ajaxResponse.add(ajaxRespUsers);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/get_region", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<AjaxRespAutocomplete> getRegion(@RequestParam("regionId") String regionKey) throws Exception {

        List<AjaxRespAutocomplete> ajaxResponse = new ArrayList<>();
        String response = null;
        try {


            List<AjaxRespAutocomplete> listRegion = branchService.region(regionKey.toUpperCase());

            if (listRegion.size() > 0) {
                for (AjaxRespAutocomplete region : listRegion) {
                    AjaxRespAutocomplete ajaxRespUsers = new AjaxRespAutocomplete();
                    ajaxRespUsers.setValue(region.getValue());
                    ajaxRespUsers.setLabel(region.getLabel());
                    ajaxResponse.add(ajaxRespUsers);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ajaxResponse;
    }

    @RequestMapping(value = "/get_roles_combo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<Users> getRole() throws Exception {

        List<Users> ajaxResponse = new ArrayList<>();
        String response = null;
        try {


            List<Users> listUsers = branchService.listRoles();

            if (listUsers.size() > 0) {
                for (Users user : listUsers) {
                    ajaxResponse.add(user);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return ajaxResponse;
    }

}
