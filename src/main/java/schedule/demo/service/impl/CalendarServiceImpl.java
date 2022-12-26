package schedule.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import schedule.demo.model.Calendar;
import schedule.demo.repository.CalendarRepository;
import schedule.demo.service.CalendarService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    @Override
    public List<Calendar> findAll(Sort sort) {
        return calendarRepository.findAll(sort);
    }

    @Override
    public List<Calendar> findAllById(Iterable<Long> longs) {
        return calendarRepository.findAllById(longs);
    }

    @Override
    public <S extends Calendar> List<S> saveAll(Iterable<S> entities) {
        return calendarRepository.saveAll(entities);
    }

    @Override
    public Calendar getCalendarById(Long id) {
        return calendarRepository.getCalendarById(id);
    }

    @Override
    public void flush() {
        calendarRepository.flush();
    }

    @Override
    public <S extends Calendar> S saveAndFlush(S entity) {
        return calendarRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Calendar> List<S> saveAllAndFlush(Iterable<S> entities) {
        return calendarRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public Calendar getById(Long aLong) {
        return calendarRepository.getById(aLong);
    }

    @Override
    public <S extends Calendar> List<S> findAll(Example<S> example) {
        return calendarRepository.findAll(example);
    }

    @Override
    public <S extends Calendar> List<S> findAll(Example<S> example, Sort sort) {
        return calendarRepository.findAll(example, sort);
    }

    @Override
    public Page<Calendar> findAll(Pageable pageable) {
        return calendarRepository.findAll(pageable);
    }

    @Override
    public <S extends Calendar> S save(S entity) {
        return calendarRepository.save(entity);
    }

    @Override
    public Optional<Calendar> findById(Long aLong) {
        return calendarRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return calendarRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return calendarRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        calendarRepository.deleteById(aLong);
    }

    @Override
    public void delete(Calendar entity) {
        calendarRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        calendarRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Calendar> entities) {
        calendarRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        calendarRepository.deleteAll();
    }

    @Override
    public <S extends Calendar> Page<S> findAll(Example<S> example, Pageable pageable) {
        return calendarRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Calendar> long count(Example<S> example) {
        return calendarRepository.count(example);
    }

    @Override
    public <S extends Calendar> boolean exists(Example<S> example) {
        return calendarRepository.exists(example);
    }

    @Override
    public <S extends Calendar, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return calendarRepository.findBy(example, queryFunction);
    }


}
