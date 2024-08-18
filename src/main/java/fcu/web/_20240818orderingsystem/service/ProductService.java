package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}