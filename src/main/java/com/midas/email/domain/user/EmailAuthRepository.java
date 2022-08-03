package com.midas.email.domain.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {

	@Query("SELECT ea FROM EmailAuth ea WHERE ea.email = :email AND ea.authToken = :authToken AND ea.expired = false AND ea.expireDate >= :currentTime")
	Optional<EmailAuth> findValidAuthByEmail(@Param("email") String email, @Param("authToken") String authToken, @Param("currentTime") LocalDateTime currentTime);
}
