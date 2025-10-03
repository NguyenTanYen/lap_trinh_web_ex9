package nguyentanyen.lap_trinh_web_ex09.controller;

import nguyentanyen.lap_trinh_web_ex09.entity.Product;
import nguyentanyen.lap_trinh_web_ex09.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "products/edit";
        }
        return "redirect:/products";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}

