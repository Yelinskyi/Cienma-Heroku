package project.cinema.spring.dao;

import project.cinema.spring.model.Order;
import project.cinema.spring.model.User;

import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
