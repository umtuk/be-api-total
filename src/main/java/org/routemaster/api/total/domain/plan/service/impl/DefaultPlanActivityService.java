package org.routemaster.api.total.domain.plan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.persistence.PlanActivityRepository;
import org.routemaster.api.total.domain.plan.service.PlanActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultPlanActivityService implements PlanActivityService {

    private final PlanActivityRepository repository;

    @Override
    @Transactional
    public Flux<PlanActivity> listByPlanGroupId(String planGroupId) {
        return repository.findAllByPlanGroupId(planGroupId);
    }

    @Override
    @Transactional
    public Mono<PlanActivity> save(PlanActivity entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Mono<PlanActivity> details(String id) {
        return repository.findById(id);
    }
}