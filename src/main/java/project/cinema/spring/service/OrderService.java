package project.cinema.spring.service;

import project.cinema.spring.model.Order;
import project.cinema.spring.model.ShoppingCart;
import project.cinema.spring.model.User;

import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
