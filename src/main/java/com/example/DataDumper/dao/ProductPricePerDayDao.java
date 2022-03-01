package com.example.DataDumper.dao;

import com.example.DataDumper.entity.ProductPricePerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Repository
public class ProductPricePerDayDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        var query = "" +
                "CREATE TABLE IF NOT EXISTS price_history (\n" +
                "  date DATE NOT NULL,\n" +
                "  price DOUBLE PRECISION NOT NULL,\n" +
                "  id integer NOT NULL\n" +
                ")" +
                "";
        int update = this.jdbcTemplate.update(query);
        System.out.println("Price History table update: " + update);
    }

    public void insertData(ProductPricePerDay productPricePerDay) {
        var query = "INSERT INTO price_history(date,price,id) VALUES(?,?,?)";
        int update = this.jdbcTemplate.update(
                query, productPricePerDay.getDate(), productPricePerDay.getPrice(), productPricePerDay.getId()
        );
    }

    public void queryPriceFor3Days(int id) {
        var query = "" +
                "SELECT * FROM price_history WHERE (date BETWEEN '"
                + LocalDate.now()
                + "' AND '"
                + LocalDate.now().plusDays(3)
                + "') AND (id='"
                + id
                + "');";
        System.out.println(query);
        this.jdbcTemplate.queryForObject(query, ProductPricePerDay.class);
    }
}
