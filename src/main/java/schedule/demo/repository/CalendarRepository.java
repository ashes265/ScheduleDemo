package schedule.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import schedule.demo.model.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar,Long>, JpaSpecificationExecutor<Calendar> {
    Calendar getCalendarById(Long id);
}
