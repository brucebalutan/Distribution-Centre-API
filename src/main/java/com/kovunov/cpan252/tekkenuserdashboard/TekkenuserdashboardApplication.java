package com.kovunov.cpan252.tekkenuserdashboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.kovunov.cpan252.tekkenuserdashboard.model.DistributionCentre;
import com.kovunov.cpan252.tekkenuserdashboard.model.Item;
import com.kovunov.cpan252.tekkenuserdashboard.model.Item.Brand; // Add this import
import com.kovunov.cpan252.tekkenuserdashboard.repository.DistributionCentreRepository;
import com.kovunov.cpan252.tekkenuserdashboard.repository.ItemRepository;

import java.math.BigDecimal;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TekkenuserdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TekkenuserdashboardApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ItemRepository itemRepository, DistributionCentreRepository dcRepository) {
		return args -> {
			var distributionCentre = dcRepository
					.save(DistributionCentre.builder().name("DC1").latitude(10.0).longitude(10.9).build());

			var distributionCentreTwo = dcRepository.save(DistributionCentre.builder().name("DC2").latitude(11.0).longitude(12.0).build());
			var distributionCentreThree = dcRepository.save(DistributionCentre.builder().name("DC3").latitude(13.0).longitude(9.0).build());
			var distributionCentreFour = dcRepository.save(DistributionCentre.builder().name("DC4").latitude(14.0).longitude(8.0).build());


			itemRepository
					.save(Item.builder()
							.name("Item1")
							.branding(Brand.Gucci) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1500.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentre)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item2")
							.branding(Brand.Balenciaga) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("2000.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentre)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item3")
							.branding(Brand.Stone_Island) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1200.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentre)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item5")
							.branding(Brand.Dior) // Set your desired brand
							.yearOfCreation(2023) // Set your desired year
							.price(new BigDecimal("1300.00")) // Set your desired price
							.quantity(20)
							.distributionCentre(distributionCentreTwo)
							.build());


			itemRepository
					.save(Item.builder()
							.name("Item6")
							.branding(Brand.Gucci) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1600.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentreThree)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item7")
							.branding(Brand.Stone_Island) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1700.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentreThree)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item8")
							.branding(Brand.Dior) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("2000.00")) // Set your desired price
							.quantity(10)
							.distributionCentre(distributionCentreFour)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Item7")
							.branding(Brand.Balenciaga) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("2100.00")) // Set your desired price
							.quantity(11)
							.distributionCentre(distributionCentreFour)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Hoodie One")
							.branding(Brand.Balenciaga) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1001.00")) // Set your desired price
							.quantity(11)
							.distributionCentre(distributionCentreFour)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Hoodie One")
							.branding(Brand.Balenciaga) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1001.00")) // Set your desired price
							.quantity(11)
							.distributionCentre(distributionCentreThree)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Hoodie One")
							.branding(Brand.Balenciaga) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1001.00")) // Set your desired price
							.quantity(11)
							.distributionCentre(distributionCentreTwo)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Shirt One")
							.branding(Brand.Stone_Island) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1005.00")) // Set your desired price
							.quantity(12)
							.distributionCentre(distributionCentre)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Shirt One")
							.branding(Brand.Stone_Island) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1005.00")) // Set your desired price
							.quantity(12)
							.distributionCentre(distributionCentreTwo)
							.build());

			itemRepository
					.save(Item.builder()
							.name("Shirt One")
							.branding(Brand.Stone_Island) // Set your desired brand
							.yearOfCreation(2022) // Set your desired year
							.price(new BigDecimal("1005.00")) // Set your desired price
							.quantity(12)
							.distributionCentre(distributionCentreThree)
							.build());
		};
	}
}
