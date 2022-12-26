package schedule.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.demo.model.CheckNoti;

public interface CheckNotiRepository extends JpaRepository<CheckNoti,Long> {
    CheckNoti findCheckNotiById(Long id);
}
