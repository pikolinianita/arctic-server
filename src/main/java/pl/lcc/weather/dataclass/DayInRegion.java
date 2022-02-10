
package pl.lcc.weather.dataclass;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author piko
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class DayInRegion implements Serializable {

    public DayInRegion( String region, LocalDate dayAvg, long extent, long area) {
        
        this.region = region;
        this.dayAvg = dayAvg;
        this.extent = extent;
        this.area = area;
    }    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id; 
    
     String region; 
    
     @Column(columnDefinition = "TIMESTAMP NULL")
     LocalDate dayAvg; 
    
     long extent; 
    
     long area;
}
