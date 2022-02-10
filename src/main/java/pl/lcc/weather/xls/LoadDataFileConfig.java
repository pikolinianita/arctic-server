
package pl.lcc.weather.xls;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author piko
 */

@Configuration
public class LoadDataFileConfig {

    @Bean 
    DataFilesLocations getDataFileLocations(){
        return new DataFiles();
    }
}
