
package pl.lcc.weather.xls;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author piko
 */


class DataFiles implements DataFilesLocations{

    //@Value("${lcc.data.dayFiles}")
    @Value("${lcc.data.testFiles}")
    List<String> locationsOfDataFiles;
    
    @Override
    public List<String> getFileNames() {
        return locationsOfDataFiles;
    }
}

interface DataFilesLocations {

    List<String> getFileNames();
}