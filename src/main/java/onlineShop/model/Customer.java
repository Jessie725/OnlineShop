package onlineShop.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer") // 用户注册信息
public class Customer implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id //
    @GeneratedValue(strategy = GenerationType.AUTO)
    // 4个column
    private int id;
    private String firstName;
    private String lastName;
    private String customerPhone;

    // cascade -- 级联操作，保存 customer 的时候自动insert 跟他相关联的 billingAddress，user，cart
    // EAGER -- 读get shippingAddress的时候把跟他相关联的 billingAddress，user，cart也读取了
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true) // 约束外键，外键的值不能有duplicate，两个表一一对应，OneToOne的时候都要写
    private ShippingAddress shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public BillingAddress getBillingAddress() {return billingAddress; }

    public void setBillingAddress(BillingAddress billingAddress) {this.billingAddress = billingAddress; }

    public ShippingAddress getShippingAddress() {return shippingAddress; }

    public void setShippingAddress(ShippingAddress shippingAddress) {this.shippingAddress = shippingAddress; }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public User getUser() {return user; }

    public void setUser(User user) {this.user = user; }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) { this.cart = cart; }
}
