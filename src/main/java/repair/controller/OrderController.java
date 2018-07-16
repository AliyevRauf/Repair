package repair.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import repair.model.AjaxRespAutocomplete;
import repair.model.Order;
import repair.model.OrderResponse;
import repair.model.Problem;
import repair.security.UserDetailsService;
import repair.security.UserPrincipal;
import repair.service.OrderService;
import repair.util.JspView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by User on 6/29/2018.
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    protected static Logger log= Logger.getLogger("OrderController");

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
     public ModelAndView getOrders(Model model) throws Exception {

     List<OrderResponse> orderResponses = orderService.listorders();

     model.addAttribute("list",orderResponses);

     return new ModelAndView(JspView.ORDER, model.asMap());
    }

    @PreAuthorize("hasAuthority('create_order')")
    @RequestMapping(value = "/add_order",method = RequestMethod.GET)
    public ModelAndView getAddOrder(
                                    Model model) throws Exception {


        List<Problem> problems = orderService.Problems();

        model.addAttribute("list",problems);

        return new ModelAndView(JspView.ADDORDER, model.asMap());
    }

    @RequestMapping(value = "/get_devices", method = RequestMethod.POST)
    @ResponseBody
    public String getDevices(@RequestParam("device") String deviceKey) throws Exception {
        log.info("getDevices");
       List<AjaxRespAutocomplete> ajaxResponse = new ArrayList<>();
       String response=null;
        try {


            List<AjaxRespAutocomplete> listDevices = orderService.device(deviceKey.toUpperCase());

            if (listDevices.size() > 0) {
                for(AjaxRespAutocomplete device : listDevices){
                    AjaxRespAutocomplete ajaxRespDevice = new AjaxRespAutocomplete();
                    ajaxRespDevice.setValue(device.getValue());
                    ajaxRespDevice.setLabel(device.getLabel());
                    ajaxResponse.add(ajaxRespDevice);

                }
            }

            Gson gson=new Gson();
             response=gson.toJson(ajaxResponse);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return response;
    }

    @RequestMapping(value = "/add_order",method = RequestMethod.POST)
    public ModelAndView postAddOrder( UsernamePasswordAuthenticationToken principal,
                                      @RequestParam(value = "pin", required = true) String pin,
                                      @RequestParam(value = "device_Id",required = true) int device_id,
                                      @RequestParam(value="problems", required = true) int[] problems,
                                      @RequestParam(value="info", required = false) String info,
                                      Model model) throws Exception {

        log.info("postAddOrder");

        UserPrincipal userPrincipal = (UserPrincipal) principal.getPrincipal();
        int userId=userPrincipal.getId();
        int branchId = userPrincipal.getBranchId();

        Order order=new Order();
        order.setBranchId(branchId);
        order.setUserId(userId);
        order.setPin(pin);
        order.setDevice_id(device_id);
        order.setProblems(problems);
        order.setInfo(info);

       boolean result = orderService.insertOrder(order);


       return new ModelAndView(JspView.RedirectOrder + "?saved=" + result, model.asMap());

    }

    @RequestMapping(value = "/edit_order/{order_id}",method = RequestMethod.GET)
    public ModelAndView getEditOrder( @PathVariable int order_id,
                                      Model model) throws Exception {

        log.info("getEditOrder");

        OrderResponse orderResponse=orderService.orderResponseById(order_id);

        List<Problem> problems= orderService.Problems();

        model.addAttribute("list",problems);


        model.addAttribute("orderResponse",orderResponse);




        return new ModelAndView(JspView.EDITORDER, model.asMap());

    }

    @RequestMapping(value = "/edit_order/{order_id}",method = RequestMethod.POST)
    public ModelAndView postEditOrder( @PathVariable int order_id,
                                       @RequestParam(value = "pin", required = true) String pin,
                                       @RequestParam(value = "device_Id",required = true) int device_id,
                                       @RequestParam(value="problems", required = true) int[] problems,
                                       @RequestParam(value="info", required = false) String info,
                                      Model model) throws Exception {

        log.info("postEditOrder");

        Order editOrder = new Order();
        editOrder.setId(order_id);
        editOrder.setPin(pin);
        editOrder.setDevice_id(device_id);
        editOrder.setProblems(problems);
        editOrder.setInfo(info);

        boolean result=orderService.editOrder(editOrder);



        return new ModelAndView(JspView.RedirectOrder + "?saved=" + result, model.asMap());
    }



}
