package exampleCarRental.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    private int id;
    private int carId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalCost;
    private RentalStatus rentalStatus;


}
