package com.example.DataDumper.controller;

import com.example.DataDumper.entity.ProductDetail;
import com.example.DataDumper.entity.ProductPricePerDay;
import com.example.DataDumper.helper.ExcelHelper;
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

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.checkExcelFormat(file)) {
            try {
                List<ProductDetail> productDetails = ExcelHelper.convertExcelToListOfProductDetail(file.getInputStream());
                for (int i = 0; i < productDetails.size(); i++) {

                }

                List<ProductPricePerDay> productPricePerDay = ExcelHelper.convertExcelToListOfProductPricePerDay(file.getInputStream());
                for (int i = 0; i < productPricePerDay.size(); i++) {
                    System.out.println(productPricePerDay.get(i));
                }
                return ResponseEntity.ok(Map.of("message", "File saved"));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().body("Some error occurred");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
    }

}
