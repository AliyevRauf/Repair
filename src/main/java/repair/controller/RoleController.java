package repair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import repair.model.Role;
import repair.service.RoleService;
import repair.util.JspView;

import java.util.List;


/**
 * Created by Rauf on 16/06/2018.
 */
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    protected static Logger log = Logger.getLogger("RoleController");

   // @PreAuthorize("hasAuthority('bonus_to_user')")

    @RequestMapping(value = "/roles",method = RequestMethod.GET)
    public ModelAndView roles(Model model) throws Exception {

        List<Role>  list = roleService.listRole();

        model.addAttribute("list",list);

        return new ModelAndView(JspView.ROLES, model.asMap());
    }

    @RequestMapping(value = "/add_roles",method = RequestMethod.GET)
    public ModelAndView addRoleGet(Model model) throws Exception {

        return new ModelAndView(JspView.ADD_ROLES, model.asMap());
    }




    @PostMapping(value = "/add_role")
    public ModelAndView addRole(Model model,
                                        @RequestParam(value = "role_name", required = true) String role_name,
                                        @RequestParam(value = "create_user", required = true,defaultValue = "") String create_user,
                                        @RequestParam(value = "create_branch", required = true,defaultValue = "false") Boolean create_branch ,
                                        @RequestParam(value = "create_order", required = true,defaultValue = "false") Boolean create_order ,
                                        @RequestParam(value = "view_orders", required = true) String view_orders) {
        log.debug("addRole");

     Role role= new Role();
     role.setRoleName(role_name);
     role.setCreatUser(create_user);
     role.setCreateBranch(create_branch.toString()+ "_branch");
     role.setCreateOrder(create_order.toString()+ "_order");
     role.setUsersOrders(view_orders);

     boolean result = roleService.createNewRole(role);
      if(result){
          model.addAttribute("saved","success");
      }else {
          model.addAttribute("saved","error");
      }


        return new ModelAndView(JspView.RedirectROLES, model.asMap());

    }

    @GetMapping(value = "/edit_role/{id}")
    public ModelAndView getEditRole(Model model,
                                 @PathVariable int id) {
        log.debug("getEditRole");

        Role roleListById =  roleService.listRoleById(id);
        model.addAttribute("role",roleListById);

        return new ModelAndView(JspView.EDIT_ROLES, model.asMap());

    }

    @PostMapping(value = "/edit_role/{id}")
    public ModelAndView postEditRole(Model model,
                                 @PathVariable int id,
                                 @RequestParam(value = "role_name", required = true) String role_name,
                                 @RequestParam(value = "create_user", required = true,defaultValue = "") String create_user,
                                 @RequestParam(value = "create_branch", required = true,defaultValue = "false") Boolean create_branch ,
                                 @RequestParam(value = "create_order", required = true,defaultValue = "false") Boolean create_order ,
                                 @RequestParam(value = "view_orders", required = true) String view_orders) {
        log.debug("postEditRole");

       Role role = new Role();
        role.setId(id);
        role.setRoleName(role_name);
        role.setCreatUser(create_user);
        role.setCreateBranch(create_branch.toString()+ "_branch");
        role.setCreateOrder(create_order.toString()+ "_order");
        role.setUsersOrders(view_orders);

        boolean result = roleService.editRole(role);
        if(result){
            model.addAttribute("saved","success");
        }else {
            model.addAttribute("saved","error");
        }



        return new ModelAndView(JspView.RedirectROLES, model.asMap());

    }


}
