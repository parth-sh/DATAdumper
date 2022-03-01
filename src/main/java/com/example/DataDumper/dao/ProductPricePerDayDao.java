package com.example.DataDumper.dao;

import com.example.DataDumper.entity.ProductPricePerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPricePerDayDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        var query = "" +
                "CREATE TABLE IF NOT EXISTS price_history (\n" +
                "  date varchar(450) NOT NULL,\n" +
                "  price DOUBLE PRECISION NOT NULL,\n" +
                "  id integer NOT NULL\n" +
                ")" +
                "";
        int update = this.jdbcTemplate.update(query);
        System.out.println("Price History table update: " + update);
    }

    public void insertData(ProductPricePerDay productPricePerDay) {
        var query = "" +
                "INSERT INTO price_history(date,price,id) VALUES(?,?,?)";
        int update = this.jdbcTemplate.update(
                query, productPricePerDay.getDate().toString(), productPricePerDay.getPrice(), productPricePerDay.getId()
        );
    }
}
