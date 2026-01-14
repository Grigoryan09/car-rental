package exampleCarRental.dto;

import exampleCarRental.model.Car;
import exampleCarRental.model.Customer;
import exampleCarRental.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

    private Rental rental;
    private Car car;
    private Customer customer;
}
