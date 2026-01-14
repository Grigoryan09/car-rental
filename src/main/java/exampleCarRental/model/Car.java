package exampleCarRental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private int id;
    private String brand;
    private String model;
    private int year;
    private BigDecimal dailyRate;
    private CarStatus status;


}
