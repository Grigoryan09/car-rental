package exampleCarRental.service;

import exampleCarRental.db.DBConnectionProvider;
import exampleCarRental.dto.RentalDto;
import exampleCarRental.model.Car;
import exampleCarRental.model.CarStatus;
import exampleCarRental.model.Customer;
import exampleCarRental.model.Rental;
import exampleCarRental.model.RentalStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class RentalService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final CarService carService = new CarService();
    private final CustomerService customerService = new CustomerService();

    public void addRental(Rental rental) {
        String query = "INSERT INTO rental(car_id, customer_id, start_date, end_date, total_cost, status)VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rental.getCarId());
            ps.setInt(2, rental.getCustomerId());
            ps.setDate(3, Date.valueOf(rental.getStartDate()));
            ps.setDate(4, Date.valueOf(rental.getEndDate()));
            ps.setBigDecimal(5, rental.getTotalCost());
            ps.setString(6, RentalStatus.ACTIVE.name());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Rental> getAllRentals() {
        updateFinishedRentals();
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rental";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rental rental = new Rental();
                rental.setId(resultSet.getInt("id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setCustomerId(resultSet.getInt("customer_id"));
                rental.setStartDate(resultSet.getDate("start_date").toLocalDate());
                rental.setEndDate(resultSet.getDate("end_date").toLocalDate());
                rental.setTotalCost(resultSet.getBigDecimal("total_cost"));
                rental.setRentalStatus(RentalStatus.valueOf(resultSet.getString("status")));
                rentals.add(rental);
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RentalDto> getRentalDto() {
        List<RentalDto> rentals = new ArrayList<>();
        getAllRentals().forEach(rental -> {
            Customer customer = customerService.getCustomerById(rental.getCustomerId());
            Car car = carService.getCarById(rental.getCarId());
            rentals.add(new RentalDto(rental, car, customer));
        });
        return rentals;
    }

    public Rental getRentalById(int id) {
        String query = "SELECT * FROM rental WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Rental rental = new Rental();
                rental.setId(resultSet.getInt("id"));
                rental.setCarId(resultSet.getInt("car_id"));
                rental.setCustomerId(resultSet.getInt("customer_id"));
                rental.setStartDate(resultSet.getDate("start_date").toLocalDate());
                rental.setEndDate(resultSet.getDate("end_date").toLocalDate());
                rental.setTotalCost(resultSet.getBigDecimal("total_cost"));
                rental.setRentalStatus(RentalStatus.valueOf(resultSet.getString("status")));
                return rental;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Rental updateTotalCost(Rental rental) {
        Car car = carService.getCarById(rental.getCarId());
        if (rental.getStartDate() != null && rental.getEndDate() != null) {
            if (rental.getEndDate().isBefore(rental.getStartDate())) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
            long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate()) + 1;
            BigDecimal dailyRate = car.getDailyRate();
            rental.setTotalCost(dailyRate.multiply(BigDecimal.valueOf(days)));
            return rental;
        } else
            throw new IllegalArgumentException("Start date and end date must not be null");

    }

    public void updateStatus(int rentalId, RentalStatus status) {
        String query = "UPDATE rental SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setLong(2, rentalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFinishedRentals() {
        String query = "UPDATE rental SET status = 'FINISHED' WHERE end_date <CURDATE() and status = 'ACTIVE'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRentalStatus(int rentalId, RentalStatus status) {
        updateStatus(rentalId, status);
        Rental rentalById = getRentalById(rentalId);
        switch (rentalById.getRentalStatus()) {
            case ACTIVE -> carService.changeCarStatus(rentalById.getCarId(), CarStatus.RENTED);
            case CANCELLED, FINISHED -> carService.changeCarStatus(rentalById.getCarId(), CarStatus.AVAILABLE);
        }
    }

    public void deleteRental(int id) {
        String sql = "DELETE FROM rental WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

