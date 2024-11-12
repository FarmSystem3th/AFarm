package com.example.farmsystem.Controller;

import com.example.farmsystem.Entity.Cart;
import com.example.farmsystem.Entity.User;
import com.example.farmsystem.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 사용자 장바구니 조회
    @GetMapping("/user/{userId}")
    public List<Cart> getCartItemsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId); // User 객체 생성 및 ID 설정
        return cartService.getCartItemsByUser(user);
    }

    // 장바구니에 상품 추가
    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId, @RequestParam Long goodsId, @RequestParam int quantity) {
        User user = new User();
        user.setId(userId); // User 객체 생성 및 ID 설정
        return cartService.addToCart(user, goodsId, quantity);
    }

    // 장바구니 아이템 삭제
    @DeleteMapping("/{cartId}")
    public void removeCartItem(@PathVariable Long cartId) {
        cartService.removeCartItem(cartId);
    }
}