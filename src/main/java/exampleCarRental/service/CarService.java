package exampleCarRental.service;

import exampleCarRental.db.DBConnectionProvider;
import exampleCarRental.model.Car;
import exampleCarRental.model.CarStatus;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addCar(Car car) {
        String sql = "INSERT INTO car (brand,model,year,daily_rate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setBigDecimal(4, car.getDailyRate());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                car.setId(rs.getInt(1));
                car.setStatus(CarStatus.AVAILABLE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCar(int id) {
        String sql = "DELETE FROM car WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Car getCarById(int id) {
        String sql = "SELECT * FROM car WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return addCar(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Car addCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setBrand(resultSet.getString("brand"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        car.setDailyRate(resultSet.getBigDecimal("daily_rate"));
        car.setStatus(CarStatus.valueOf(resultSet.getString("status")));
        return car;
    }

    public void changeCarStatus(int id, CarStatus status) {
        String sql = "UPDATE car SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Car> getAlLCar() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                cars.add( addCar(resultSet));
            }
            return  cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Car> findAvailableCars(LocalDate start, LocalDate end) {
        List<Car> cars = new ArrayList<>();
        String sql = " SELECT * FROM cars WHERE status = 'AVAILABLE' AND id NOT IN ( SELECT car_id FROM rentals WHERE status = 'ACTIVE'AND (start_date <= ? AND end_date >= ?))";

        try (PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, CarStatus.AVAILABLE.name());
            ps.setString(2, "ACTIVE");
            ps.setDate(1, Date.valueOf(end));
            ps.setDate(2, Date.valueOf(start));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBigDecimal("daily_rate"),
                        CarStatus.valueOf(rs.getString("status"))
                );
                cars.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cars;
    }
}




