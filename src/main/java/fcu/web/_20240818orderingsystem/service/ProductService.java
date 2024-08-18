package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Product;
import fcu.web._20240818orderingsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}