package io.github.funpilot.demo.mktdata.repo;

import io.github.funpilot.demo.mktdata.model.Exchange;
import io.github.funpilot.demo.mktdata.model.Price;
import io.github.funpilot.demo.mktdata.model.PriceKey;
import io.micronaut.transaction.annotation.ReadOnly;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.List;
import java.util.Optional;

@Singleton
public class PriceRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    public PriceRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @ReadOnly
    public Optional<Price> findByKey(PriceKey key) {
        return Optional.ofNullable(entityManager.find(Price.class, key));
    }

    @ReadOnly
    public Optional<Price> findLatest(String symbol, Exchange exchange) {
        String queryStr = """
            SELECT p
            FROM Price as p
            WHERE p.symbol = :symbol
            AND p.exchange = :exchange
            ORDER BY tradeDate DESC
            """;
        TypedQuery<Price> query = entityManager.createQuery(queryStr, Price.class);
        query.setParameter("symbol", symbol);
        query.setParameter("exchange", exchange);

        List<Price> list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.getFirst());
        }
    }

    @ReadOnly
    public List<Price> findAll() {
        String queryStr = "SELECT p FROM Price as p";
        TypedQuery<Price> query = entityManager.createQuery(queryStr, Price.class);
        return query.getResultList();
    }

    @Transactional
    public void save(Price price) {
        entityManager.persist(price);
    }
}
