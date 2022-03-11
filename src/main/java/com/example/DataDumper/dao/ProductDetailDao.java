package com.example.DataDumper.dao;

import com.example.DataDumper.entity.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDetailDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable() {
        var query = "" +
                "DROP TABLE IF EXISTS product_details;" +
                "CREATE TABLE IF NOT EXISTS product_details (\n" +
                "  interest_rate DOUBLE PRECISION NOT NULL,\n" +
                "  maturity_date DATE NOT NULL,\n" +
                "  name varchar(450) NOT NULL,\n" +
                "  id integer PRIMARY KEY NOT NULL\n" +
                ");";
        int update = this.jdbcTemplate.update(query);
        System.out.println("Product detail table update: " + update);
    }

    public void insertData(ProductDetail productDetail) {
        var query = "" +
                "INSERT INTO product_details(id,name,maturity_date,interest_rate) VALUES(?,?,?,?)";
        int update = this.jdbcTemplate.update(
                query, productDetail.getId(), productDetail.getName(), productDetail.getMaturity_date(), productDetail.getInterest_rate()
        );
    }

    public List<?> queryDetailByDate(int id, String date) {
        var query = "SELECT price,name,interest_rate " +
                "FROM product_details " +
                "INNER JOIN price_history " +
                "ON product_details.id=price_history.id " +
                "WHERE product_details.id='" + id + "' AND date='" + date + "';";
        return this.jdbcTemplate.queryForList(query);
    }
}
