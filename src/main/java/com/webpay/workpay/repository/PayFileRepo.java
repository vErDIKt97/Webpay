package com.webpay.workpay.repository;

import com.webpay.workpay.domain.PayFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayFileRepo extends JpaRepository<PayFile,Long> {
    PayFile findByFileName(String fileName);
}
