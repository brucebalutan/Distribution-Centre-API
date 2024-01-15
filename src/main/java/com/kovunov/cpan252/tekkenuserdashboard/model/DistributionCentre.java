package com.kovunov.cpan252.tekkenuserdashboard.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Clarifications: admin page is a page in Clothes Wareshouse app, which is
 * available only to admins
 * Items are stored in databases in BOTH projects, and should have similar
 * structure
 * We DON'T have to add security to Distribution Centre app
 * Item === ClothesItem
 * For presentation, we have these options: 
 * One person presents everything, shows some interactions and just goes through the bullet points
 * as shows that this functionality is available in the app
 * You select pieces of functionality and present them separately
 */
public class DistributionCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    //This is to connect DistributionCentre to ClothesItem
    @OneToMany(mappedBy = "distributionCentre")
    private List<Item> items;

}
