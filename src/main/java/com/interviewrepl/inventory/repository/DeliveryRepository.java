package com.interviewrepl.inventory.repository;

import com.interviewrepl.inventory.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long>
{

}

