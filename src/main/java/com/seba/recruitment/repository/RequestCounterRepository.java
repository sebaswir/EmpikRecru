package com.seba.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seba.recruitment.model.RequestCounter;

@Repository
public interface RequestCounterRepository extends JpaRepository<RequestCounter, String> {

}
