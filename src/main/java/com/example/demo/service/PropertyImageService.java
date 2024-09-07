package com.example.demo.service;

import com.example.demo.model.PropertyImage;
import com.example.demo.repository.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyImageService {

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    public List<PropertyImage> getAllPropertyImages() {
        return propertyImageRepository.findAll();
    }

    public PropertyImage createPropertyImage(PropertyImage propertyImage) {
        return propertyImageRepository.save(propertyImage);
    }

    public PropertyImage getPropertyImageById(Long id) {
        Optional<PropertyImage> image = propertyImageRepository.findById(id);
        return image.orElse(null);
    }

    public PropertyImage updatePropertyImage(Long id, PropertyImage propertyImage) {
        if (propertyImageRepository.existsById(id)) {
            propertyImage.setId(id);
            return propertyImageRepository.save(propertyImage);
        }
        return null;
    }

    public void deletePropertyImage(Long id) {
        propertyImageRepository.deleteById(id);
    }
}
