/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.lcc.weather.xls;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.GenericApplicationContext;
import pl.lcc.weather.persistence.DaysRepo;

/**
 *
 * @author piko
 */
//properties="spring.main.lazy-initialization=true"
@SpringBootTest (classes = LoadDataFileConfig.class)
public class LoadDataRunnerTest {
    
    @Mock
    DaysRepo repo;
    
    @Mock
    XlxFileReader reader;
    
    @Autowired
    GenericApplicationContext ctx;
    
    @Test
    void testValueInjection() {
    var result = new LoadDataRunner(repo, ctx.getBean(DataFilesLocations.class));
    System.out.println(result.files.getFileNames());
    assertThat(result.files.getFileNames()).isNotEmpty();
    
    
   
    
    }
    
}
