package pl.lcc.weather.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.lcc.weather.controller.DayDto;
import pl.lcc.weather.dataclass.DayInRegion;

/**
 *
 * @author piko
 */
@Service
public class CrudService {

    DaysRepo repo;

    public CrudService(DaysRepo repo) {
        this.repo = repo;
    }

    public Object count() {
        return repo.count();
    }

    public List<String> getAreas() {
        return repo.findDistinctRegion();
    }

    public List<DayDto> getData(String area, long startDay, long endDay) {
        if (endDay == 0) {
            endDay = startDay;
        }
        if (area.toLowerCase().equals("all")) {
            return repo.findByDayAvgBetween(LocalDate.ofEpochDay(startDay), LocalDate.ofEpochDay(endDay))
                    .stream().map(DayDto::new).toList();
        } else {
            return repo.findByDayAvgBetweenAndRegion(LocalDate.ofEpochDay(startDay), LocalDate.ofEpochDay(endDay), area)
                    .stream().map(DayDto::new).toList();
        }
    }

    public List<Long> getMinMaxDates() {

        return List.of(
                repo.findDates(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "dayAvg"))).get(0).toEpochDay(),
                repo.findDates(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "dayAvg"))).get(0).toEpochDay()
        );
    }

}
