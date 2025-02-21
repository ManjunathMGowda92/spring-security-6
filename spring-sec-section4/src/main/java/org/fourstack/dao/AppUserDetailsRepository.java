package org.fourstack.dao;

import org.fourstack.entity.AppUserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserDetailsRepository extends MongoRepository<AppUserDetails, String> {

  Optional<AppUserDetails> findByUsernameIgnoreCase(String username);

  void deleteByUsernameIgnoreCase(String username);
}
