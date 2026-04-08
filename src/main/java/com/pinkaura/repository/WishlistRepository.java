package com.pinkaura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.pinkaura.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByUserId(int userId);

    @Modifying
    @Transactional
    void deleteByUserIdAndProductId(int userId, int productId);
}