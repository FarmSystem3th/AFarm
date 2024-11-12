package com.example.farmsystem.Service;


import com.example.farmsystem.Entity.Cart;
import com.example.farmsystem.Entity.Goods;
import com.example.farmsystem.Entity.User;
import com.example.farmsystem.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final GoodsService goodsService;

    public CartService(CartRepository cartRepository, GoodsService goodsService) {
        this.cartRepository = cartRepository;
        this.goodsService = goodsService;
    }

    public List<Cart> getCartItemsByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public Cart addToCart(User user, Long goodsId, int quantity) {
        Goods goods = goodsService.findGoodsById(goodsId);
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setGoods(goods);
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
