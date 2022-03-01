package com.example.DataDumper.controller;

import com.example.DataDumper.dao.ProductDetailDao;
import com.example.DataDumper.dao.ProductPricePerDayDao;
import com.example.DataDumper.entity.ProductDetail;
import com.example.DataDumper.entity.ProductPricePerDay;
import com.example.DataDumper.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductDetailDao productDetailDao;

    @Autowired
    ProductPricePerDayDao productPricePerDayDao;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.checkExcelFormat(file)) {
            try {
                List<ProductDetail> productDetails = ExcelHelper.convertExcelToListOfProductDetail(file.getInputStream());
                for (int i = 0; i < productDetails.size(); i++) {
                    this.productDetailDao.insertData(productDetails.get(i));
                }

                List<ProductPricePerDay> productPricePerDay = ExcelHelper.convertExcelToListOfProductPricePerDay(file.getInputStream());
                for (int i = 0; i < productPricePerDay.size(); i++) {
                    this.productPricePerDayDao.insertData(productPricePerDay.get(i));
                }
                return ResponseEntity.ok(Map.of("message", "File saved"));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
    }

}
