package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pgciric.entity.Delivery;

public interface DeliveryRepo extends JpaRepository<Delivery, Integer>{
	
	@Query("select d from delivery d where MONTH(date)=:month")
	public List<Delivery> findDeliveryByMonth(int month);
	
	@Query("select d from delivery d where YEAR(date)=:year")
	public List<Delivery> findDeliveryByYear(int year);

	@Query("select d from delivery d where MONTH(date)=:month AND YEAR(date)=:year order by d.date asc")
	public List<Delivery> findDeliveryByYearAndMonth(int month, int year);
	
	@Query(value = "select distinct year(pg_ciric.delivery.date) from pg_ciric.delivery", nativeQuery = true)
	public List<String> findAllYearWithExistDelivery();

}
