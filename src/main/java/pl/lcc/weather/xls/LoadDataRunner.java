
package pl.lcc.weather.xls;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.lcc.weather.persistence.DaysRepo;

/**
 *
 * @author piko
 */
@Slf4j
@Component
public class LoadDataRunner implements ApplicationRunner{

    
    
    DaysRepo repo;    
    
    DataFilesLocations files;

    public LoadDataRunner(DaysRepo repo, DataFilesLocations  files) {
        this.repo = repo;
        this.files = files;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
         log.info("loading file");
         log.info(files.getFileNames().toString());
         files.getFileNames().forEach(this::loadFile);
       
        
        log.info("file loaded");
    }

    private void loadFile(String path) {
        
        XlxFileReader reader = new XlxFileReader(path);
        
        log.info("will Load file" + path);

//last sheet is Documentation        
        int numberOfRegions = (reader.getSheetsNumber() -1 ) / 2;
        for (int i = 0; i < numberOfRegions; i++){
             repo.saveAll(reader.populateArea(i * 2));
        }
    }

}
