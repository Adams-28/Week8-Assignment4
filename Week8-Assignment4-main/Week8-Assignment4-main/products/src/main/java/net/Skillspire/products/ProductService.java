package net.Skillspire.products;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product p) {
        p.setId(null);
        return productRepository.save(p);
    }

    public Product updateProduct(Long id, Product p) {
        Product existing = productRepository.findById(id).orElse(null);

        if(existing == null) {
            return null;
        }

        existing.setName((p.getName()));
        existing.setCategory(p.getCategory());
        existing.setPrice(p.getPrice());
        existing.setStockQuantity(p.getStockQuantity());

        return productRepository.save(existing);
    }


    public boolean deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);

        return true;
    }

    //Method for finding a product's category
    public String getProductCategory(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product not found"
        ));

        return product.getCategory();
    }

    //Filtering methods for Max price
    public List<Product> searchProducts(String category, double maxPrice) {

        if (maxPrice < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Max Price cannot be below 0!"
            );
        }

        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .filter(product -> product.getPrice() <= maxPrice)
                .toList();
    }

    // Patch Product Method
    public Product patchProduct(Long id, ProductPatchRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        if (request.name() != null) {
            product.setName(request.name());
        }

        if (request.category() != null) {
            product.setCategory(request.category());
        }

        if (request.price() != null) {
            if (!Double.isFinite(request.price()) || request.price() < 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Price must be higher than 0!"
                );
            }

            product.setPrice(request.price());
        }

        return productRepository.save(product);
    }





}
