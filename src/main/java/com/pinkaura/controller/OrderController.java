package com.pinkaura.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.pinkaura.model.Orders;
import com.pinkaura.model.Cart;
import com.pinkaura.model.OrderItem;

import com.pinkaura.repository.OrderRepository;
import com.pinkaura.repository.CartRepository;
import com.pinkaura.repository.OrderItemRepository;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;
    private final OrderItemRepository orderItemRepo;

    public OrderController(OrderRepository orderRepo, CartRepository cartRepo, OrderItemRepository orderItemRepo){
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.orderItemRepo = orderItemRepo;
    }

    @PostMapping("/place")
    public Orders placeOrder(@RequestBody Orders order){

        // 1. Save order
        Orders savedOrder = orderRepo.save(order);

        // 2. Get cart items
        List<Cart> cartItems = cartRepo.findByUserId(order.getUserId());

        // 3. Save each item
        for(Cart c : cartItems){

            OrderItem item = new OrderItem();
            item.setOrderId(savedOrder.getId());
            item.setProductId(c.getProductId());
            item.setQuantity(c.getQuantity());
            item.setPrice(c.getProduct().getPrice());

            orderItemRepo.save(item);
        }

        // 4. Clear cart
        cartRepo.clearCart(order.getUserId());

        return savedOrder;
    }
}