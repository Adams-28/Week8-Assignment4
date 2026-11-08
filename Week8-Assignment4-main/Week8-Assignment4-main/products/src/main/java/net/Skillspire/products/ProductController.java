package net.Skillspire.products;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable("id") Long id) {
        Product p = service.getProduct(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product p) {
        Product saved = service.addProduct(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product p) {
        Product updated = service.updateProduct(id, p);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id") Long id) {

        boolean deleted = service.deleteProduct(id);

        System.out.println("Product entry deleted!");

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    // Adding category endpoint
    @GetMapping("/{id}/category")
    public ResponseEntity<String> getProductCategory(
            @PathVariable("id") Long id,
            //Combining parameter and path variable
            @RequestParam(
                    value = "uppercase",
                    required = false,
                    defaultValue = "false"
            ) boolean uppercase) {

        String category = service.getProductCategory(id);

        if (uppercase) {
            category = category.toUpperCase();
        }

        return ResponseEntity.ok(category);
    }


    // Adding search endpoint
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam String category,
            // Setting max price
            @RequestParam(
                    value = "maxPrice",
                    required = false,
                    defaultValue = "10"
            ) double maxPrice) {

        List<Product> products = service.searchProducts(category, maxPrice);

        return ResponseEntity.ok(products);
    }

    // Adding Patching method
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody ProductPatchRequest request) {

        Product updatedProduct = service.patchProduct(id, request);

        return ResponseEntity.ok(updatedProduct);
    }
}
