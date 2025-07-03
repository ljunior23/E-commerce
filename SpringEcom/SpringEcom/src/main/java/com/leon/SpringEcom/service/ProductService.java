package com.leon.SpringEcom.service;

import com.leon.SpringEcom.model.Products;
import com.leon.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepo productRepo;

    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    public Products getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public Products addOrUpdateProduct(Products product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productRepo.save(product);
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Products> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
