package com.example.demo.controller;

import com.example.demo.model.PropertyImage;
import com.example.demo.service.PropertyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/propertyImages")
public class PropertyImageController {

    @Autowired
    private PropertyImageService propertyImageService;

    @GetMapping
    public ResponseEntity<List<PropertyImage>> getAllPropertyImages() {
        List<PropertyImage> images = propertyImageService.getAllPropertyImages();
        return ResponseEntity.ok(images);
    }

    @PostMapping
    public ResponseEntity<PropertyImage> createPropertyImage(@RequestBody PropertyImage propertyImage) {
        PropertyImage createdImage = propertyImageService.createPropertyImage(propertyImage);
        return ResponseEntity.ok(createdImage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyImage> getPropertyImageById(@PathVariable Long id) {
        PropertyImage image = propertyImageService.getPropertyImageById(id);
        return ResponseEntity.ok(image);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyImage> updatePropertyImage(@PathVariable Long id, @RequestBody PropertyImage propertyImage) {
        PropertyImage updatedImage = propertyImageService.updatePropertyImage(id, propertyImage);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePropertyImage(@PathVariable Long id) {
        propertyImageService.deletePropertyImage(id);
        return ResponseEntity.ok("Property image deleted successfully!");
    }
}
