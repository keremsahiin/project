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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Resource
    private CartRepository cartRepository;

    @Resource
    private SessionService sessionService;

    @Resource
    private ProductService productService;

    @Override
    public Cart getCartForCustomer(Customer customer) throws Exception {
        final Optional<Cart> optionalCart = cartRepository.findCartByCustomer(customer);
        if (optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            calculateCart(cart);
        }else {
            new Cart();
        }
        return optionalCart.orElse(new Cart());
    }

    @Override
    public void addProductToCart(String productCode, int quantity) throws Exception {
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
            calculateCart(cart);
            cartRepository.save(cart);

        } else {
            throw new Exception("User not found.");
        }
    }

    //stream araştır!!
    @Override
    public void removeProductFromCart(String productCode) throws Exception {
        final Customer currentCustomer = sessionService.getCurrentCustomer();
        if (Objects.nonNull(currentCustomer)) {
            final Cart cart = sessionService.getCurrentCart();
            final Set<Entry> entries = new HashSet<>(cart.getEntries());

            Entry targetEntry = entries.stream().filter(entry -> entry.getProduct().getCode().equals(productCode)).findFirst()
                    .orElseThrow(() -> new Exception("Entry is Not found" + productCode));
            entries.remove(targetEntry);

            cart.setEntries(entries);
            calculateCart(cart);
            cartRepository.save(cart);

        } else {
            throw new Exception("User not found.");
        }
    }

    @Override
    public void updateCart(String productCode, int quantity) throws Exception {
        Product product = productService.getProductForCode(productCode);
        Cart cart = sessionService.getCurrentCart();
        if (quantity < product.getStockValue()) {
            if (quantity <= 0) {
                throw new Exception("Wrong parameter" + productCode);
            }
            if (Objects.nonNull(cart)) {
                Entry entry = cart.getEntries().stream().filter(entry1 -> entry1.getProduct().equals(product)).findFirst()
                        .orElseThrow();
                entry.setQuantity(quantity);
                calculateCart(cart);
                cartRepository.save(cart);
            } else {
                throw new Exception();
            }
        }
    }

    @Override
    public void emptyCart() throws Exception {
        Cart cart = sessionService.getCurrentCart();
        if (Objects.nonNull(cart)){
            cartRepository.delete(cart);
            cartRepository.save(cart);
        }
    }

    @Override
    public void calculateCart(Cart cart) throws Exception {
        double total = 0;
        if(CollectionUtils.isEmpty(cart.getEntries())){
            throw new Exception("Its empty!!");
        }else {
            for (Entry entry : cart.getEntries()) {
                Double price = entry.getProduct().getPrice();
                Integer quantity = entry.getQuantity();
                total = total + (price * quantity);
            }
        }
        cart.setTotalPriceOfProducts(total);
        cartRepository.save(cart);
    }


}
