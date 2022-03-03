package com.example.DataDumper.helper;

import com.example.DataDumper.entity.ProductDetail;
import com.example.DataDumper.entity.ProductPricePerDay;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static boolean checkExcelFormat(MultipartFile file) {
        return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<ProductDetail> convertExcelToListOfProductDetail(InputStream is) {
        List<ProductDetail> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Product Details");
            int row_number = 0;
            Iterator iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = (Row) iterator.next();
                if (row_number == 0) {
                    row_number++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                ProductDetail productDetail = new ProductDetail();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            productDetail.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            productDetail.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            productDetail.setMaturity_date(cell.getStringCellValue());
                            break;
                        case 3:
                            productDetail.setInterest_rate(cell.getNumericCellValue() * 100);
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                if (productDetail.getId() == 0) break;
                list.add(productDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<ProductPricePerDay> convertExcelToListOfProductPricePerDay(InputStream is) {
        List<ProductPricePerDay> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Prices Per Day");
            int row_number = 0;
            Iterator iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = (Row) iterator.next();
                if (row_number == 0) {
                    row_number++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                ProductPricePerDay productPricePerDay = new ProductPricePerDay();
                String dateValueCheck = null;
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            DataFormatter formatter = new DataFormatter();
                            String strValue = formatter.formatCellValue(cell);
                            dateValueCheck = strValue;
                            if (dateValueCheck == "") break;
                            SimpleDateFormat dateFormat;
                            try {
                                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                productPricePerDay.setDate(dateFormat.parse(strValue));
                            } catch (Exception e) {
                                try {
                                    dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                    productPricePerDay.setDate(dateFormat.parse(strValue));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            break;
                        case 1:
                            productPricePerDay.setPrice(cell.getNumericCellValue());
                            break;
                        case 2:
                            productPricePerDay.setId((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                if (dateValueCheck == "") break;
                list.add(productPricePerDay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
