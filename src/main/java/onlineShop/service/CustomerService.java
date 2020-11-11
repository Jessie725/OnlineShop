package onlineShop.service;

import onlineShop.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.CustomerDao;
import onlineShop.model.Customer;

@Service // 也是一个 component， 专属service -- 业务逻辑相关
public class CustomerService {

    @Autowired // spring 注入
    private CustomerDao customerDao; // 在customerDao由spring自动创建，injection过来的

    public void addCustomer(Customer customer) { // Customer从controller来，http输入
        Cart cart = new Cart();
        customer.setCart(cart); // 给这个customer创建一个cart
        customer.getUser().setEnabled(true); // 给这个用户登录权限
        customerDao.addCustomer(customer);
    }

    public Customer getCustomerByUserName(String userName) {
        return customerDao.getCustomerByUserName(userName);
    }
}


