package schedule.demo.service;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import schedule.demo.model.CheckNoti;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CheckNotiService {

    CheckNoti findCheckNotiById(Long id);

    List<CheckNoti> findAll();

    <S extends CheckNoti> S saveAndFlush(S entity);

    <S extends CheckNoti> S save(S entity);

    Optional<CheckNoti> findById(Long aLong);

    boolean existsById(Long aLong);
}
