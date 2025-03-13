package org.fourstack.dao;

import org.fourstack.entity.Loans;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends MongoRepository<Loans, String> {
}
