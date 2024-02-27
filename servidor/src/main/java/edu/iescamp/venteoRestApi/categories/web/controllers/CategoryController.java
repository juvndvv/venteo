package edu.iescamp.venteoRestApi.categories.web.controllers;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryDetailsDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryOverviewDTO;
import edu.iescamp.venteoRestApi.categories.models.dto.CategoryPatchDTO;
import edu.iescamp.venteoRestApi.categories.repositories.entities.Category;
import edu.iescamp.venteoRestApi.categories.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryOverviewDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id,
                           @Valid @RequestBody Category category) {
        category.setCategoryId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }

    @PatchMapping("/category/{id}")
    public ResponseEntity<Category> updatePartial(@PathVariable Long id,
                                  @Valid @RequestBody CategoryPatchDTO category) {
        category.setCategoryId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
    }

    @GetMapping("/category/{id}/auctions")
    public ResponseEntity<List<Auction>> findAuctionsByCategoryId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.findAuctionsByCategoryId(id));
    }
}
