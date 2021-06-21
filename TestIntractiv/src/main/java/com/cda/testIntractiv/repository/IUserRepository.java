package com.cda.testIntractiv.repository;

import org.springframework.data.repository.CrudRepository;

import com.cda.testIntractiv.model.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

}
