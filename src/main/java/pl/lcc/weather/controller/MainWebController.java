
package pl.lcc.weather.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lcc.weather.dataclass.DayInRegion;
import pl.lcc.weather.persistence.CrudService;

/**
 *
 * @author piko
 */
@CrossOrigin
@RestController
public class MainWebController {

//    @Autowired
//    DaysRepo repo;    
//    
    CrudService repoService;
    
    public MainWebController(CrudService repoService) {
        this.repoService = repoService;
    }  
    
    @GetMapping("/count")
    Object getRecordsAmount(){
        return repoService.count();
    }
    
    //area can be given area or all
  
    @GetMapping({"{area}/{startDay}/{endDay}", "{area}/{startDay}"})
            List<DayDto> getData(
                    @PathVariable String area,                    
                    @PathVariable long startDay, 
                    @PathVariable(required = false) long endDay){
        return repoService.getData(area, startDay, endDay);
    }
    
     @GetMapping("/areas")    
     List<String> getAreas(){
         return repoService.getAreas();
     }
     
     @GetMapping("/dates")
     List<Long> getMinMaxDates(){
        return repoService.getMinMaxDates();   
     }      
         
}
