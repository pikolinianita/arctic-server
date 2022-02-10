
package pl.lcc.weather.xls;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import pl.lcc.weather.dataclass.DayInRegion;

/**
 *
 * @author piko
 */
@Slf4j
public class XlxFileReader {

    Workbook workbook;
   
    public XlxFileReader(String path) {
               
        try (var nFile = new ClassPathResource(path).getInputStream()){
            workbook = new XSSFWorkbook(nFile);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {

            return "XlxFileReader{" + "1st sheet of workbook=" + workbook.getSheetName(0) + '}';
        }
    
    List<DayInRegion> populateArea(int sheetNumber) {
        var AreaIterator = workbook.getSheetAt(sheetNumber).iterator();
        var ExtentIterator = workbook.getSheetAt(++sheetNumber).iterator();
        String name = findAreaName(workbook.getSheetAt(sheetNumber).getSheetName());
        log.info("Starting processing: " + name);
        return new AreaProcessor(name, AreaIterator, ExtentIterator).process();
    }

    DayInRegion getOneTestDay() {
        return new DayInRegion("Arctic", LocalDate.of(1022, 1, 22), 1000L, 500L);
            }

    public int getSheetsNumber(){
        return workbook.getNumberOfSheets();
    }
    
    String findAreaName(String sheetName) {
        return sheetName.substring(0, sheetName.lastIndexOf("-" , sheetName.lastIndexOf("-") - 1)).replace('-', ' '); 
    }

    static class AreaProcessor {

        String name; 
        Iterator<Row> areaIterator; 
        Iterator<Row> extentIterator;
        int month;
        
        public AreaProcessor(String name, Iterator<Row> areaIterator, Iterator<Row> extentIterator) {
        this.areaIterator = areaIterator;
        this.extentIterator = extentIterator;
        this.name = name;
        this.month = 0;
        }
        
        public List<DayInRegion> process() {
            List<DayInRegion> resultList = new ArrayList<>();
            //skip header
           var firstRow = areaIterator.next();
            extentIterator.next(); 
            
            while (areaIterator.hasNext()) {
                int firstYearOfMeasurementsDec2 = (int) firstRow.getCell(2).getNumericCellValue() - 2;
                var areaRow = areaIterator.next();
                var extentRow = extentIterator.next();
                int day = (int) areaRow.getCell(1).getNumericCellValue();
                
                if (!areaRow.getCell(0).getStringCellValue().isBlank()) {
                    month++;
                }               
                for (int i = 2; i < areaRow.getLastCellNum(); i++) {                
                    if (areaRow.getCell(i) != null && areaRow.getCell(i).getCellType() != BLANK) {
                        resultList.add(new DayInRegion(name,
                                LocalDate.of( i + firstYearOfMeasurementsDec2,  month, day),
                                (long) areaRow.getCell(i).getNumericCellValue(),
                                (long) extentRow.getCell(i).getNumericCellValue()
                        ));
                    }
                }
            }
            return resultList;
        }
    }
    
    
    
}
