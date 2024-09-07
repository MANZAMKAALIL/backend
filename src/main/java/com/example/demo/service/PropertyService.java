package com.example.demo.service;

import com.example.demo.model.Property;
import com.example.demo.model.PropertyImage;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        property.setName(propertyDetails.getName());
        property.setDescription(propertyDetails.getDescription());
        property.setPrice(propertyDetails.getPrice());
        property.setPropertyType(propertyDetails.getPropertyType());
        property.setLocation(propertyDetails.getLocation());
        property.setStatus(propertyDetails.getStatus());
        property.setTenantId(propertyDetails.getTenantId());
        property.setAgentId(propertyDetails.getAgentId());

        // Update new fields
        property.setVirtualTourUrl(propertyDetails.getVirtualTourUrl());
        property.setLatitude(propertyDetails.getLatitude());
        property.setLongitude(propertyDetails.getLongitude());

        return propertyRepository.save(property);
    }

    public Property uploadPropertyImages(Long id, List<MultipartFile> files) throws IOException {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));

        List<PropertyImage> propertyImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = "/images/" + fileName; // Save the path where the image will be stored

            // Save the file to the server (implement file saving logic here)

            PropertyImage propertyImage = new PropertyImage();
            propertyImage.setFileName(fileName);
            propertyImage.setFilePath(filePath);
            propertyImage.setProperty(property);

            propertyImages.add(propertyImage);
        }

        property.getImages().addAll(propertyImages);
        propertyRepository.save(property);

        return property;
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
