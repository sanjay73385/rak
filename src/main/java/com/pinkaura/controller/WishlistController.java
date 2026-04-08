package com.pinkaura.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.pinkaura.model.Wishlist;
import com.pinkaura.repository.WishlistRepository;

@RestController
@CrossOrigin
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistRepository wishlistRepository;

    public WishlistController(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    // ADD
    @PostMapping("/add")
    public Wishlist add(@RequestBody Wishlist wishlist){
        return wishlistRepository.save(wishlist);
    }

    // GET
    @GetMapping("/{userId}")
    public List<Wishlist> getWishlist(@PathVariable int userId){
        return wishlistRepository.findByUserId(userId);
    }

    @DeleteMapping("/remove/{productId}/{userId}")
    public String remove(@PathVariable int productId, @PathVariable int userId){
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
        return "Removed";
    }
}