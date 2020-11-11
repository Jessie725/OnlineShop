package onlineShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

    @RequestMapping(value = "/order/{cartId}", method = RequestMethod.GET)
    public String createOrder(@PathVariable("cartId") int cartId) {
        // redirect 到 check out 界面
        return "redirect:/checkout?cartId=" + cartId;
    }
}


