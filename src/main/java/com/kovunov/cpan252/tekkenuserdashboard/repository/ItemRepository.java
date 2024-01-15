package com.kovunov.cpan252.tekkenuserdashboard.repository;

import com.kovunov.cpan252.tekkenuserdashboard.model.DistributionCentre;
import com.kovunov.cpan252.tekkenuserdashboard.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByBranding(Item.Brand brand);
    List<Item> findByName(String name);

    List<Item> findByBrandingAndYearOfCreation(Item.Brand brand, int yearOfCreation);

    List<Item> findByNameAndBranding(String name, Item.Brand brand);
    List<Item> findByOrderByBranding();
    List<Item> findByNameAndDistributionCentre(String name, DistributionCentre distributionCentre);
    List<Item> findByBrandingAndDistributionCentre(Item.Brand brand, DistributionCentre distributionCentre);




}
