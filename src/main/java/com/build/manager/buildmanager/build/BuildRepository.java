package com.build.manager.buildmanager.build;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BuildRepository extends ReactiveMongoRepository<BuildModel, String>{
}