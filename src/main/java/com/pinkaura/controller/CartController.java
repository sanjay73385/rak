package com.pinkaura.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.pinkaura.model.Cart;
import com.pinkaura.repository.CartRepository;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @PostMapping("/add")
    public Cart add(@RequestBody Cart cart){

        Cart existing = cartRepository
            .findByUserIdAndProductId(cart.getUserId(), cart.getProductId());

        if(existing != null){
            existing.setQuantity(existing.getQuantity() + 1);
            return cartRepository.save(existing);
        }

        return cartRepository.save(cart);
    }

    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable int userId){
        return cartRepository.findByUserId(userId);
    }

    @PutMapping("/decrease/{id}")
    public Cart decrease(@PathVariable int id){

        Cart cart = cartRepository.findById(id).orElse(null);

        if(cart != null){
            if(cart.getQuantity() > 1){
                cart.setQuantity(cart.getQuantity() - 1);
                return cartRepository.save(cart);
            } else {
                cartRepository.deleteById(id);
            }
        }

        return null;
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable int id){
        cartRepository.deleteById(id);
        return "Removed";
    }
}