package com.example.OnlineBookStore.controller;

import com.example.OnlineBookStore.entity.Order;
import com.example.OnlineBookStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //  Place a new order
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Order order) {
        String username = userDetails.getUsername();
        Order placedOrder = orderService.placeOrder(username, order);
        return ResponseEntity.ok(placedOrder);
    }

    // Get order details by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    //  Get all orders (Admin-only)
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    //  Get user-specific order history
    @GetMapping("/history")
    public ResponseEntity<List<Order>> getUserOrderHistory(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }

    // Cancel an order
    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order canceled successfully!");
    }
}
