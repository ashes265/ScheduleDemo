package schedule.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import schedule.demo.model.CheckNoti;
import schedule.demo.repository.CheckNotiRepository;
import schedule.demo.service.CheckNotiService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CheckNotiServiceImpl implements CheckNotiService {
    @Autowired
    private CheckNotiRepository checkNotiRepository;

    @Override
    public CheckNoti findCheckNotiById(Long id) {
        return checkNotiRepository.findCheckNotiById(id);
    }

    @Override
    public List<CheckNoti> findAll() {
        return checkNotiRepository.findAll();
    }

    @Override
    public <S extends CheckNoti> S saveAndFlush(S entity) {
        return checkNotiRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends CheckNoti> S save(S entity) {
        return checkNotiRepository.save(entity);
    }

    @Override
    public Optional<CheckNoti> findById(Long aLong) {
        return checkNotiRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return checkNotiRepository.existsById(aLong);
    }
}
