package org.fourstack.dao;

import org.fourstack.entity.CustomerInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends MongoRepository<CustomerInfo, String> {

  Optional<CustomerInfo> findByEmailIgnoreCase(String email);

  void deleteByEmailIgnoreCase(String email);
}
