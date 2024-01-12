package com.demo.demo.service.impl;

import com.demo.demo.entity.Cart;
import com.demo.demo.entity.Customer;
import com.demo.demo.entity.Entry;
import com.demo.demo.entity.Product;
import com.demo.demo.repository.CartRepository;
import com.demo.demo.service.CartService;
import com.demo.demo.service.ProductService;
import com.demo.demo.service.SessionService;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;

    @Resource
    private SessionService sessionService;

    @Resource
    private ProductService productService;

    @Override
    public Cart getCartForCustomer(Customer customer) {
        final Optional<Cart> optionalCart = cartRepository.findCartByCustomer(customer);
        return optionalCart.orElse(new Cart());
    }

    @Override
    public boolean addProductToCart(String productCode, int quantity) throws Exception {
        final Customer currentCustomer = sessionService.getCurrentCustomer();
        if (Objects.nonNull(currentCustomer)) {
            final Cart cart = sessionService.getCurrentCart();
            final Set<Entry> entries = new HashSet<>(cart.getEntries());

            final Product product = productService.getProductForCode(productCode);
            final Entry entry = new Entry();
            entry.setProduct(product);
            entry.setQuantity(quantity);
            entry.setEntryPrice(product.getPrice() * quantity);

            entries.add(entry);
            cart.setEntries(entries);
            cartRepository.save(cart);

            return true;
        } else {
            throw new Exception("User not found.");
        }
    }

    //stream araştır!!
    @Override
    public boolean removeProductFromCart(String productCode) throws Exception {
        final Customer currentCustomer = sessionService.getCurrentCustomer();
        if (Objects.nonNull(currentCustomer)) {
            final Cart cart = sessionService.getCurrentCart();
            final Set<Entry> entries = new HashSet<>(cart.getEntries());

            Entry targetEntry = entries.stream().filter(entry -> entry.getProduct().getCode().equals(productCode)).findFirst()
                    .orElseThrow(() -> new Exception("Entry is Not found" + productCode));
            entries.remove(targetEntry);

            cart.setEntries(entries);
            cartRepository.save(cart);

            return true;
        } else {
            throw new Exception("User not found.");
        }
    }

}
