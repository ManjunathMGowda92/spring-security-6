package org.fourstack.dao;

import org.fourstack.entity.Accounts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends MongoRepository<Accounts, String> {

  Optional<Accounts> findByCustomerId(String customerId);
}
