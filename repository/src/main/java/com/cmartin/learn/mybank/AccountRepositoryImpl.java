package com.cmartin.learn.mybank;

import com.cmartin.learn.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository<Account, UUID> {

    private final ConcurrentMap<UUID, Account> repository = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> save(final Account entity) {
        final UUID id = UUID.randomUUID();
        entity.setId(id);
        this.repository.put(id, entity);

        return Optional.of(entity);
    }

    @Override
    public Optional<Account> findOne(final UUID primaryKey) {
        return Optional.ofNullable(this.repository.get(primaryKey));
    }

    @Override
    public Iterable<Account> findAll() {
        return this.repository.values();
    }

    @Override
    public Long count() {
        return Long.valueOf(this.repository.size());
    }

    @Override
    public void delete(final Account entity) {
        this.repository.remove(entity.getId());
    }

    @Override
    public Boolean exists(final UUID primaryKey) {
        final Optional<Account> account = this.findOne(primaryKey);

        return account.isPresent();
    }
}
