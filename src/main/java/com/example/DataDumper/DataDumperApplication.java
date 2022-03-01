package com.example.DataDumper;

import com.example.DataDumper.dao.ProductDetailDao;
import com.example.DataDumper.dao.ProductPricePerDayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataDumperApplication implements CommandLineRunner {

    @Autowired
    ProductDetailDao productDetailDao;

    @Autowired
    ProductPricePerDayDao productPricePerDayDao;

    public static void main(String[] args) {
        SpringApplication.run(DataDumperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productDetailDao.createTable();
        productPricePerDayDao.createTable();
    }
}
