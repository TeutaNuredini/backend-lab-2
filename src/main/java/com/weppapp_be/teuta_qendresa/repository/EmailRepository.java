package com.weppapp_be.teuta_qendresa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.weppapp_be.teuta_qendresa.entity.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
}
