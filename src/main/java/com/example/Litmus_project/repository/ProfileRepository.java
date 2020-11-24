package com.example.Litmus_project.repository;

import com.example.Litmus_project.domain.DomainProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<DomainProfile,Long> {

    Optional<List<DomainProfile>> findByName(String name);
    Optional<List<DomainProfile>> findByMobileNo(Long mobileNo);
    Optional<DomainProfile> findByEmail(String aLong);

}
