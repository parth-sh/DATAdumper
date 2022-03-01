package com.example.DataDumper.helper;

import com.example.DataDumper.entity.ProductDetail;
import com.example.DataDumper.entity.ProductPricePerDay;
import org.apache.poi.ss.usermodel.Cell;
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
//            e.printStackTrace();
        }
        return list;
    }

    private static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
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
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            try {
                                productPricePerDay.setDate(cell.getDateCellValue());
                            } catch (Exception e) {
                                String date = cell.getStringCellValue();
                                if (isValidFormat("dd-MMM-yyyy", date)) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                    productPricePerDay.setDate(dateFormat.parse(date));
                                }
                                if (isValidFormat("dd/mm/yy", date)) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
                                    productPricePerDay.setDate(dateFormat.parse(date));
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
                list.add(productPricePerDay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
