
//checking

package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.CandidateEntity;

public interface CandidateRepo extends JpaRepository<CandidateEntity, Integer>{

}
