package repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repair.model.*;
import repair.service.UserService;
import repair.util.Constant;
import repair.util.JspView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/11/2018.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users(Model model) throws Exception {

       List<Users> usersList = userService.users();

       model.addAttribute("usersList",usersList);


        return new ModelAndView(JspView.USERS,model.asMap());
    }

    @PreAuthorize("hasAuthority('order_view_all')")
    @RequestMapping(value = "/view_order/{userId}", method = RequestMethod.GET)
    public ModelAndView getViewUsers(@PathVariable int userId,
                                     Model model) throws Exception {

    List<OrderResponse> orderResponseList =userService.listorders(userId);

       model.addAttribute("orderResponseList", orderResponseList);


        return new ModelAndView(JspView.VIEWORDER, model.asMap());
    }


    @RequestMapping(value = "/get_branch", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<AjaxRespAutocomplete> getBranch(@RequestParam("branchName") String branchKey) throws Exception {

        List<AjaxRespAutocomplete> ajaxResponse = new ArrayList<>();
        try {


            List<AjaxRespAutocomplete> listUsers = userService.branch(branchKey.toUpperCase());

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


    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public ModelAndView getAddUser(Model model) throws Exception {


        return new ModelAndView(JspView.ADDUSER, model.asMap());
    }


    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public @ResponseBody ResponseModel postAddBranch(@RequestParam("branches") String branches,
                                @RequestParam("roles") String roles,
                                @RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("email") String email,
                                @RequestParam("j_phone") String tel,
                                @RequestParam("password") String password

    ) throws Exception {

        String[] branchMass = branches.split("--");
        String[] rolesMass = roles.split("--");
        List<Branch> branchList = new ArrayList();
        if (branchMass.length == rolesMass.length)
            for (int i = 0; i < branchMass.length && !branchMass[i].isEmpty(); i++) {
                Branch b = new Branch();
                int branchId = Integer.parseInt(branchMass[i]);
                int roleID = Integer.parseInt(rolesMass[i]);
                b.setBranchId(branchId);
                b.setRoleId(roleID);
                branchList.add(b);
            }

       Users users= new Users();
       users.setName(name);
       users.setSurname(surname);
       users.setEmail(email);
       users.setTel(tel);
       users.setPassword(password);
       users.setBranches(branchList);

        boolean result = userService.insertUser(users);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(Constant.OKAY_CODE);
        responseModel.setTitle(Constant.OKAY_TITLE);
        return responseModel;
    }

    @RequestMapping(value = "/edit_user", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseModel postEditBranch(
            @RequestParam("userId") int userId,
            @RequestParam("buid") String buid,
            @RequestParam("branches") String branches,
            @RequestParam("roles") String roles,
            @RequestParam("deleted") String deleted,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("j_phone") String j_phone,
            @RequestParam("password") String password

    ) throws Exception {

        System.out.println(roles);
        System.out.println(userId);
        if (deleted != "") {
            String[] massDeleted = deleted.split("aa");
            for (int i = 0; i < massDeleted.length; i++) {
                boolean delete = userService.deleteBranchUsers(Integer.parseInt(massDeleted[i]));
            }
        }
        String[] massRole = roles.split("aa");
        String[] massBranches= branches.split("aa");
        if (buid != "") {
            String[] massBu = buid.split("aa");

            if (massBranches.length == massRole.length) {
                for (int i = 0; i < massBu.length; i++) {
                    if (Integer.parseInt(massBu[i]) != -1) {
                       boolean update = userService.updateBranchUsers(Integer.parseInt(massBu[i]), Integer.parseInt(massRole[i]));
                    } else if (Integer.parseInt(massBu[i]) == -1) {
                        boolean insert = userService.insertBranchUser(Integer.parseInt(massBranches[i]),userId,Integer.parseInt(massRole[i]));
                    }

                }

            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(Constant.OKAY_CODE);
        responseModel.setTitle(Constant.OKAY_TITLE);
        return responseModel;
    }

    @RequestMapping(value = "/edit_user/{userId}", method = RequestMethod.GET)
    public ModelAndView getEditBranch(@PathVariable int userId,
                                      Model model) throws Exception {

        Users users = userService.listUserByUserId(userId);
        List<Users> roleList = userService.listRoles();

       model.addAttribute("users", users);
        model.addAttribute("roleList", roleList);


        return new ModelAndView(JspView.EDITUSER, model.asMap());
    }


}
