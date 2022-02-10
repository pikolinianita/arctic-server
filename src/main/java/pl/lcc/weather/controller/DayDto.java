
package pl.lcc.weather.controller;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.lcc.weather.dataclass.DayInRegion;

/**
 *
 * @author piko
 */
@AllArgsConstructor
@Getter
@Setter
public class DayDto {
    
    String region; 
    
    LocalDate day; 
    
    long extent; 
    
    long area;

    public DayDto(DayInRegion source) {
        this.region = source.getRegion();
        this.day = source.getDayAvg();
        this.extent = source.getExtent();
        this.area = source.getArea();
    }
    
    
}
