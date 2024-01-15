package com.kovunov.cpan252.tekkenuserdashboard.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.kovunov.cpan252.tekkenuserdashboard.model.dto.DistributionCentreDto;
import com.kovunov.cpan252.tekkenuserdashboard.model.dto.ItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kovunov.cpan252.tekkenuserdashboard.model.DistributionCentre;
import com.kovunov.cpan252.tekkenuserdashboard.model.dto.DistributionCentreDto;
import com.kovunov.cpan252.tekkenuserdashboard.model.Item;
import com.kovunov.cpan252.tekkenuserdashboard.repository.DistributionCentreRepository;
import com.kovunov.cpan252.tekkenuserdashboard.repository.ItemRepository;

@RestController
@RequestMapping("/api/distribution")
public class DistributionCentreController {

    private final DistributionCentreRepository dcRepository;
    private final ItemRepository itemRepository;

    public DistributionCentreController(DistributionCentreRepository dcRepository, ItemRepository itemRepository) {
        this.dcRepository = dcRepository;
        this.itemRepository = itemRepository;
    }


    @GetMapping
    public ResponseEntity<List<DistributionCentreDto>> getAllCentres() {
        List<DistributionCentreDto> distributionCentreDtos = StreamSupport
                .stream(dcRepository.findAll().spliterator(), false)
                .map(dc -> new DistributionCentreDto(
                        dc.getId(),
                        dc.getName(),
                        dc.getLatitude(),
                        dc.getLongitude(),
                        dc.getItems()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(distributionCentreDtos);
    }


        @GetMapping("/{id}/items")
        public List<Item> getItemsForCentre(@PathVariable int id) {
            var currentDistributionCentre = dcRepository.findById(id);
            var items = currentDistributionCentre.get().getItems();
            return items;
        }

        @PostMapping("/{id}/items")
        public Item addItemToCentre(@PathVariable int id, @RequestBody Item item) {
            var currentDistributionCentre = dcRepository.findById(id);
            item.setDistributionCentre(currentDistributionCentre.get());
            var savedItem = itemRepository.save(item);
            return savedItem;
        }

        @GetMapping("/requestItem/{name}")
        public List<ItemDto> requestItemByName(
                @PathVariable String name) {
            // Placeholder logic - replace this with your actual implementation
            List<Item> items = itemRepository.findByName(name);


            if (!items.isEmpty()) {
                // Convert the items to DTOs if needed
                List<ItemDto> itemDtos = items.stream()
                        .map(item -> new ItemDto(
                                item.getId(),
                                item.getName(),
                                item.getBranding(),
                                item.getYearOfCreation(),
                                item.getPrice(),
                                item.getQuantity()))
                        .collect(Collectors.toList());

                return itemDtos;
            }
            return Collections.emptyList();
    }

    @PutMapping("/{centreId}/items/{itemId}")
    public ResponseEntity<String> updateItemInDistributionCentre(
            @PathVariable int centreId,
            @PathVariable int itemId,
            @RequestBody Item updatedItem) {
        Optional<DistributionCentre> optionalCentre = dcRepository.findById(centreId);

        if (optionalCentre.isPresent()) {
            DistributionCentre currentCentre = optionalCentre.get();

            // Find the item in the current distribution centre
            Optional<Item> existingItemOptional = currentCentre.getItems()
                    .stream()
                    .filter(item -> item.getId() == itemId)
                    .findFirst();

            if (existingItemOptional.isPresent()) {
                Item existingItem = existingItemOptional.get();

                // Update the item properties
                existingItem.setName(updatedItem.getName());
                existingItem.setBranding(updatedItem.getBranding());
                existingItem.setYearOfCreation(updatedItem.getYearOfCreation());
                existingItem.setPrice(updatedItem.getPrice());
                existingItem.setQuantity(updatedItem.getQuantity());

                // Save the changes to the item
                itemRepository.save(existingItem);
                dcRepository.save(currentCentre);

                return ResponseEntity.ok("Item in Distribution Centre updated successfully");
            } else {
                // Handle case where the item is not found in the distribution centre
                return ResponseEntity.notFound().build();
            }
        } else {
            // Handle case where the distribution centre is not found
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/requestBrand/{brand}")
    public List<ItemDto> requestItemByBrand(@PathVariable String brand) {
        // Placeholder logic - replace this with your actual implementation
        List<Item> items = itemRepository.findByBranding(Item.Brand.valueOf(brand));

        if (!items.isEmpty()) {
            // Logic to handle successful item request
            // For example, update stock, deduct from distribution centre, etc.

            // Convert the items to DTOs if needed
            List<ItemDto> itemDtos = items.stream()
                    .map(item -> new ItemDto(
                            item.getId(),
                            item.getName(),
                            item.getBranding(),
                            item.getYearOfCreation(),
                            item.getPrice(),
                            item.getQuantity()))
                    .collect(Collectors.toList());

            return itemDtos;
        }

        return Collections.emptyList();
    }
    @GetMapping("/requestNameAndBrand/{name}/{brand}")
    public List<ItemDto> requestItemByNameAndBrand(@PathVariable String name,@PathVariable String brand) {
        // Placeholder logic - replace this with your actual implementation
        List<Item> items = itemRepository.findByNameAndBranding(name, Item.Brand.valueOf(brand));

        if (!items.isEmpty()) {
            // Logic to handle successful item request
            // For example, update stock, deduct from distribution centre, etc.

            // Convert the items to DTOs if needed
            List<ItemDto> itemDtos = items.stream()
                    .map(item -> new ItemDto(
                            item.getId(),
                            item.getName(),
                            item.getBranding(),
                            item.getYearOfCreation(),
                            item.getPrice(),
                            item.getQuantity()))
                    .collect(Collectors.toList());

            return itemDtos;
        }

        return Collections.emptyList();
    }

}
