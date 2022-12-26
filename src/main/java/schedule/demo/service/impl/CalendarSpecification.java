package schedule.demo.service.impl;

import org.springframework.data.jpa.domain.Specification;
import schedule.demo.model.Calendar;

public class CalendarSpecification {
    public static Specification<Calendar> hasType(String type){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), "%"+type+"%"));
    }

    public static Specification<Calendar> hasId(long calendarId) {
        return (root, query, cb) -> cb.equal(root.get("id"), calendarId);
    }

}
