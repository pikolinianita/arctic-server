/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.lcc.weather.persistence;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.lcc.weather.controller.DayDto;
import pl.lcc.weather.dataclass.DayInRegion;

/**
 *
 * @author piko
 */
public interface DaysRepo extends CrudRepository< DayInRegion, Long> {

    @Query("Select Distinct region From DayInRegion")
    public List<String> findDistinctRegion();

    @Query("Select d.dayAvg From DayInRegion d")
    public List<LocalDate> findDates(Pageable page);

    public List<DayInRegion> findByDayAvgBetween(LocalDate ofEpochDay, LocalDate ofEpochDay0);

    public List<DayInRegion> findByDayAvgBetweenAndRegion(LocalDate ofEpochDay, LocalDate ofEpochDay0, String area);
}
