package com.example.demo.Repository;

import com.example.demo.Model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class RentalRepo
{
    @Autowired
    JdbcTemplate template;
    public List<Rental> fetchAll()
    {
        String sql = "select * from Rentals";
        RowMapper<Rental> rowmapper = new BeanPropertyRowMapper<>(Rental.class);
        return template.query(sql, rowmapper);
    }

    public Rental createRental(Rental r)
    {
        String sql = "insert into Rentals (rental_id, documentation_id, customer_id, from_date, to_date, days_of_rental, total_price, rent_table, rent_chairs, rent_car_seat, rent_bike_rack, rent_bed_linnen) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, r.getRental_id(), r.getDocumentation_id(), r.getCustomer_id(), r.getFrom_date(), r.getTo_date(), r.getDays_of_rental(), r.getTotal_price(), r.isRent_table(), r.isRent_chairs(), r.isRent_car_seat(), r.isRent_bike_rack(), r.isRent_bed_linnen());
        return null;
    }

    public Rental findRentalById(int rental_id)
    {
        String sql = "SELECT * FROM Rentals where rental_id = ?";
        RowMapper<Rental> rowMapper = new BeanPropertyRowMapper<>(Rental.class);
        Rental r = template.queryForObject(sql, rowMapper,rental_id);
        return r;
    }

    public Boolean deleteRental(int rental_id)
    {
        String sql = "DELETE FROM Rentals WHERE rental_id = ?";
        return template.update(sql, rental_id) < 0;
    }

    public Rental updateRental(int rental_id, Rental r)
    {
        String sql = "UPDATE Rentals set total_price = ? WHERE rental_id = ?";
        System.out.println("SQL '"+ sql + "'");
        System.out.println("pris " + r.getTotal_price()+" id "+r.getRental_id());
        template.update(sql, r.getTotal_price(), r.getRental_id());
        System.out.println("Kommer koden mon ogsaa her til? " + r.getTotal_price());
        return r;
    }

}