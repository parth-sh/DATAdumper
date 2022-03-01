package com.example.DataDumper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDetailDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        var query = "" +
                "CREATE TABLE IF NOT EXISTS product_details (\n" +
                "  interest_rate DOUBLE PRECISION NOT NULL,\n" +
                "  maturity_date varchar(450) NOT NULL,\n" +
                "  name varchar(450) NOT NULL,\n" +
                "  id integer NOT NULL\n" +
                ")" +
                "";
        int update = this.jdbcTemplate.update(query);
        System.out.println("Product detail table update: " + update);
    }
}
