package repair.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repair.model.Contractor;
import repair.service.ContractorService;
import repair.util.JspView;

import java.util.List;


/**
 * Created by Rauf on 27/06/2018.
 */
@Controller
public class ContractorController {

    @Autowired
   ContractorService contractorService;

    protected static Logger log= Logger.getLogger("ContractorController");

    @RequestMapping(value = "/contractors",method = RequestMethod.GET)
    public ModelAndView contractors(Model model) throws Exception {

        List<Contractor> list = contractorService.listContractor();

        model.addAttribute("list",list);

        return new ModelAndView(JspView.CONTRACTOR, model.asMap());
    }

    @RequestMapping(value = "/add_conractor",method = RequestMethod.GET)
    public ModelAndView addContractorGet(Model model) throws Exception {

        return new ModelAndView(JspView.ADDCONTRACTOR, model.asMap());
    }



    @RequestMapping(value = "/add_conractor",method = RequestMethod.POST)
    public ModelAndView addContractorPost(@RequestParam(value = "company_name",required = true, defaultValue = "") String company_name,
                                      @RequestParam(value = "address",required = false)String address,
                                      @RequestParam(value = "phone",required = true,defaultValue = "") String phone,
            Model model) throws Exception {

        Contractor contractor= new Contractor();
        contractor.setCompanyName(company_name);
        contractor.setAddress(address);
        contractor.setPhone(phone);

        boolean result = contractorService.addContractor(contractor);

        if(result){
            model.addAttribute("saved","success");
        }else {
            model.addAttribute("saved","error");
        }

        return new ModelAndView(JspView.RedirectCONTRACTOR, model.asMap());
    }

    @RequestMapping(value = "/edit_contractor/{id}",method = RequestMethod.GET)
    public ModelAndView getEditContractor(@PathVariable int id,
                                          Model model) throws Exception {

        log.debug("getEditRole");

        Contractor listById =  contractorService.listbyId(id);
        model.addAttribute("list",listById);


        return new ModelAndView(JspView.EDITCONTRACTOR, model.asMap());
    }

    @PostMapping(value = "/edit_contractor/{id}")
    public ModelAndView postEditContractor(Model model,
                                     @PathVariable int id,
                                     @RequestParam(value = "company_name",required = true, defaultValue = "") String company_name,
                                     @RequestParam(value = "address",required = false)String address,
                                     @RequestParam(value = "phone",required = true,defaultValue = "") String phone) {
        log.debug("postEditContractor");

        Contractor contractor = new Contractor();
        contractor.setId(id);
        contractor.setCompanyName(company_name);
        contractor.setAddress(address);
        contractor.setPhone(phone);

        boolean result = contractorService.edit(contractor);
        if (result) {
            model.addAttribute("saved", "success");
        } else {
            model.addAttribute("saved", "error");
        }

        return new ModelAndView(JspView.RedirectCONTRACTOR, model.asMap());
    }
}
