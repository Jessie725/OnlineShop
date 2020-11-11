package onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import onlineShop.model.Customer;
import onlineShop.service.CustomerService;


@Controller // 处理前端发来的http request
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/registration", method = RequestMethod.GET)
    // 当前端发送了/customer/registration，并且以GET为method
    public ModelAndView getRegistrationForm() {
        Customer customer = new Customer();
        // 找registration.jsp文件，返回新的页面
        return new ModelAndView("register", "customer", customer);
    }

    // 前端 submit 操作
    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    // @ModelAttribute submit的内容绑定到customer
    public ModelAndView registerCustomer(@ModelAttribute(value = "customer") Customer customer, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        // result 是绑定后的结果
        if (result.hasErrors()) {
            modelAndView.setViewName("register");
            return modelAndView;
        }
        customerService.addCustomer(customer); //添加到db
        modelAndView.setViewName("login"); // 返回到login的页面
        modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
        return modelAndView;
    }
}


