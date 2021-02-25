package com.tata.assignment.business;

import com.tata.assignment.dao.CartDao;
import com.tata.assignment.dao.CartDaoImpl;
import com.tata.assignment.entities.Product;

import java.sql.SQLException;
import java.util.List;

public class CartOps {
    CartDao cartDao = new CartDaoImpl();

    public void displayCart() throws SQLException {
        List<Product> list = cartDao.displayCart();
        if (list == null)
            System.out.println("Empty cart");
        else
            for (Product product : list) {
                System.out.println(product.getProductId() + " "+ product.getName()+ " "+product.getPrice()+" "+product.getQuantity());
            }
    }

    public int displayTotal() throws SQLException{
        return cartDao.displayAmount();
    }
}
