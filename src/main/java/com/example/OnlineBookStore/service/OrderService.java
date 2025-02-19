package com.example.OnlineBookStore.service;

import com.example.OnlineBookStore.entity.Order;
import com.example.OnlineBookStore.entity.User;
import com.example.OnlineBookStore.entity.Book;
import com.example.OnlineBookStore.repository.OrderRepository;
import com.example.OnlineBookStore.repository.UserRepository;
import com.example.OnlineBookStore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Order placeOrder(String username, Order order) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(order.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        order.setUser(user);
        order.setBook(book);
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELED");
        orderRepository.save(order);
    }
}
