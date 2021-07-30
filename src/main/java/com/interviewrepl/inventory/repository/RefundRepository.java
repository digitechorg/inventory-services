package com.interviewrepl.inventory.repository;

import com.interviewrepl.inventory.model.Refund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends CrudRepository<Refund, Long>
{

}

