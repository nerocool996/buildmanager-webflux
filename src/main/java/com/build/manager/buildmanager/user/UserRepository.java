package com.build.manager.buildmanager.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserModel, String> {
    public Mono<UserModel> findByEmail(String email);
}