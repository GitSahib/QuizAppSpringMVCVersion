package com.mems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mems.domain.Profile;
import com.mems.domain.User;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByFirstName(String FirstName);
    Profile findByUser(User user);
}
