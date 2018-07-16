package repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repair.model.Branch;
import repair.service.UserService;
import repair.util.JspView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) throws Exception {

    List<Branch> branchList = userService.listBranch();

    model.addAttribute("branchList",branchList);

    return new ModelAndView(JspView.LOGIN,model.asMap());
    }

}
