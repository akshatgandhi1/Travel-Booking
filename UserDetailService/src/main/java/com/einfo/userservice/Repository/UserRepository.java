package com.einfo.userservice.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.einfo.userservice.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUseremail(String username);
}
