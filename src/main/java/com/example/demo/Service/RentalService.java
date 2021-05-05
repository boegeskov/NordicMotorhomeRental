package com.example.demo.Service;

import com.example.demo.Repository.RentalRepo;
import com.example.demo.Model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RentalService
{
    @Autowired
    RentalRepo rentalrepo;
    public List<Rental> fetchAll()
    {
        return rentalrepo.fetchAll();
    }

    public Rental createRental(Rental r)
    {
        return rentalrepo.createRental(r);
    }

    public Rental findRentalById(int rental_id)
    {
        return rentalrepo.findRentalById(rental_id);
    }

    public Boolean deleteRental(int id)
    {
        return rentalrepo.deleteRental(id);
    }

    public Rental updateRental(int id, Rental r)
    {
        return rentalrepo.updateRental(id, r);
    }

    public double setSeasonPrice(Rental r)
    {
        r.setTotal_price(r.getTotal_price()* prisfaktor());
        return r.getTotal_price();
    }

    public double prisfaktor()   //fixME
    {
        boolean highSeason = false;
        boolean middleseason = true;
        boolean lowseason = false;

        if(highSeason)
        {
            return 1.60;
        }
        if(middleseason)
        {
            return 1.30;
        }
        return 1.0;
    }

    public int tankFilled()
    {
        boolean isTankFilled = true;

        if(isTankFilled)
        {
            return 0;
        }
        return 70;
    }

    public double setTankPrice(Rental r)
    {
        r.setTotal_price(r.getTotal_price()+tankFilled());
        return r.getTotal_price();
    }
}
