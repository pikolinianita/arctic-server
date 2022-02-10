/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.weather.xls;


import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



/**
 *
 * @author piko
 */
 //@SpringBootTest
 //@Import(ParserConfig.class)
 class XlxFileReaderTest {

    static XlxFileReader excellFile;
    
    @BeforeAll
    static void setup(){
        excellFile = new XlxFileReader("N_Sea_Ice_test.xlsx");
    }
        
    @Test
    public void testSomeMethod() {        
        var result = excellFile.toString(); 
        System.out.println("--------" + result + "--------");
        assertThat(result).as("should be something").isNotEmpty();
    }
    
     
    @Test
    void lookforNameTest(){
       var result = excellFile.findAreaName("Baffin-Area-km^2");
       assertThat(result).isEqualTo("Baffin");
       var result2 = excellFile.findAreaName("St-Lawrence-Area-km^2");
       assertThat(result2).isEqualTo("St Lawrence");
    }
    
    @Test
    public void readOneAreaTest() {
        SoftAssertions softly = new SoftAssertions();
        
        var result = excellFile.populateArea(0);
        
        // then
        softly.assertThat(result.size()).as("more than 20 yrs").isGreaterThan(20 * 365);
        softly.assertThat(result)
                .allMatch(day -> day.getArea() > 0, "area should be positive")
                .allMatch(day -> day.getExtent() > 0, "extent should be positive")
                .allMatch(day -> day.getDayAvg().isAfter(LocalDate.of(1978,1,1)), "proper Date min")
                .allMatch(day -> day.getDayAvg().isBefore(LocalDate.now().plusDays(3)), "proper Date max");
                
        softly.assertAll();
    }
}
