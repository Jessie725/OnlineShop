package onlineShop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Authorities;
import onlineShop.model.Customer;
import onlineShop.model.User;

@Repository // spring annotation, spring 会自动创建这个对象Dao，数据库相关
public class CustomerDao {

    @Autowired // 自动 inject 进来
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER"); // 普通用户权限
        authorities.setEmailId(customer.getUser().getEmailId());
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction(); // 涉及到多张表的操作，用transaction 保证写入save的原子性 atomic
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback(); // 如有异常，那么回到 db 之前的状态
        } finally {
            if (session != null) {
                session.close(); // 是关catch里的session
            }
        }
    }

    public Customer getCustomerByUserName(String userName) {
        User user = null;
        // try with resource, try后面定义的session会自动关
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class); //hibernate 给的 API,可以加限制条件,生成 sql语句
            user = (User) criteria.add(Restrictions.eq("emailId", userName)).uniqueResult(); // 看user表里的emailId和传入的 userName是否一样, uniqueResult, 找到一个就返回
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null)
            return user.getCustomer();
        return null;
    }
}
