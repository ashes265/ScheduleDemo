package schedule.demo.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import schedule.demo.model.Calendar;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CalendarService  {
    List<Calendar> findAll();

    List<Calendar> findAll(Sort sort);

    List<Calendar> findAllById(Iterable<Long> longs);

    <S extends Calendar> List<S> saveAll(Iterable<S> entities);

    Calendar getCalendarById(Long id);

    void flush();

    <S extends Calendar> S saveAndFlush(S entity);

    <S extends Calendar> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    Calendar getById(Long aLong);

    <S extends Calendar> List<S> findAll(Example<S> example);

    <S extends Calendar> List<S> findAll(Example<S> example, Sort sort);

    Page<Calendar> findAll(Pageable pageable);

    <S extends Calendar> S save(S entity);

    Optional<Calendar> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Calendar entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Calendar> entities);

    void deleteAll();

    <S extends Calendar> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Calendar> long count(Example<S> example);

    <S extends Calendar> boolean exists(Example<S> example);

    <S extends Calendar, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
