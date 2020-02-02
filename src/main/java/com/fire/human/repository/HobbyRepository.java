package com.fire.human.repository;

import com.fire.human.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, String> {
}
