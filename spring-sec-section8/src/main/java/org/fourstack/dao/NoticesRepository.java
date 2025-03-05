package org.fourstack.dao;

import org.fourstack.entity.Notices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticesRepository extends MongoRepository<Notices, String> {
}
