package com.kth.searchapi.web.controller;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

import com.kth.searchapi.application.service.RestaurantAdminService;
import com.kth.searchapi.domain.Restaurant;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantAdminRestController {

  private final RestaurantAdminService restaurantAdminService;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadData(@RequestPart("file") MultipartFile file) {
    // Implement file upload logic here
    // You can use a library like Apache POI to read Excel files

    // Once you have the data, call the service to process it
    List<Restaurant> restaurants = processDataFromExcel(file);

    restaurantAdminService.addRestaurantDocuments(restaurants);

    return ResponseEntity.ok("File uploaded and processed successfully!");
  }

  private List<Restaurant> processDataFromExcel(MultipartFile file) {
    List<Restaurant> dataList = new ArrayList<>();

    try (InputStream inputStream = file.getInputStream()) {
      Workbook workbook = WorkbookFactory.create(inputStream);

      // Assuming the data is in the first sheet, you can modify this if needed
      Sheet sheet = workbook.getSheetAt(0);

      // Iterate over rows
      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();

        // Skip the header row
        if (row.getRowNum() == 0) {
          continue;
        }

        // Iterate over cells in the row
        Iterator<Cell> cellIterator = row.cellIterator();
        int columnIndex = 0;
        String name = null;
        String category1 = null;
        String category2 = null;
        String category3 = null;
        String city = null;
        String area = null;
        String description = null;
        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();

          // Set the value in the Restaurant object based on the column index
          switch (columnIndex) {
            case 0:
              name = getStringCellValue(cell);
              break;
            case 1:
              category1 = getStringCellValue(cell);
              break;
            case 2:
              category2 = getStringCellValue(cell);
              break;
            case 3:
              category3 = getStringCellValue(cell);
              break;
            case 4:
              city = getStringCellValue(cell);
              break;
            case 5:
              area = getStringCellValue(cell);
              break;
            case 6:
              description = getStringCellValue(cell);
              break;
            // Add more cases if you have more columns
          }

          columnIndex++;
        }

        dataList.add(Restaurant.create(name, category1, category2, category3, city, area, description));
      }

//      workbook.close(); // deprecated
    } catch (IOException e) {
      // Handle exceptions appropriately
      throw new RuntimeException(e);
    } catch (InvalidFormatException e) {
      throw new RuntimeException(e);
    }

    return dataList;
  }


  private static String getStringCellValue(Cell cell) {
    // Convert cell value to String
    if (cell.getCellType() == CELL_TYPE_STRING) {
      return cell.getStringCellValue();
    } else if (cell.getCellType() == CELL_TYPE_NUMERIC) {
      // You might need to handle numeric values differently based on your requirements
      return String.valueOf(cell.getNumericCellValue());
    } else {
      return null; // Handle other data types as needed
    }
  }
}
