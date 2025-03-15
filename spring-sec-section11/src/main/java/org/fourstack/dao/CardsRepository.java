package org.fourstack.dao;

import org.fourstack.entity.Cards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends MongoRepository<Cards, String> {
}
