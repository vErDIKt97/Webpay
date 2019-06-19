package com.webpay.workpay.repository;

import com.webpay.workpay.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
