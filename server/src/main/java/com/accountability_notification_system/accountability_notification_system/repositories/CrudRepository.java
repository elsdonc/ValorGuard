package com.accountability_notification_system.accountability_notification_system.repositories;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.accountability_notification_system.accountability_notification_system.model.User;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {

  <S extends T> S save(S entity);
  
  <S extends T> Iterable<S> saveAll(Iterable<S> entities);
  
  Optional<T> findById(ID id);
  User findByEmail(String email);
  
  boolean existsById(ID id);
  boolean existsByEmail(String email);
  
  Iterable<T> findAll();
  
  Iterable<T> findAllById(Iterable<ID> ids);
  
  long count();
  
  void deleteById(ID id);
  void deleteByEmail(String email);
  
  void delete(T entity);
  
  void deleteAllById(Iterable<? extends ID> ids);
  
  void deleteAll(Iterable<? extends T> entities);
  
  void deleteAll();
}