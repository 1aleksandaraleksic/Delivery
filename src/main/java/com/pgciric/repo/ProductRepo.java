package com.pgciric.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pgciric.entity.MilkyProduct;
import com.pgciric.entity.Product;
import com.pgciric.entity.ProductResult;
import com.pgciric.entity.ProductResultStats;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	@Query("select p from product p where p.category.id=:categoryId")
	public List<Product> productsByCategory(int categoryId);
	
	@Query("select p from product p where p.name like %?1%")
	public List<Product> searchProduct(String search);
	
	@Query("select p from product p where p.name like %?1% and p.category.id=?2")
	public List<Product> searchProductByCategory(@Param("search") String search, @Param("categoryId") int categoryId);
	
	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure, round(sum(oi.quantity),2),count(d.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "where d.id =:deliveryId "
			+ "group by p.id "
			+ "order by p.category")
	public List<ProductResult> totalProductsForDelivery(@Param("deliveryId") int deliveryId);
	
	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure, round(sum(oi.quantity),2),count(d.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "where MONTH(d.date) =:month AND YEAR(d.date) =:year "
			+ "group by p.id "
			+ "order by p.category")
	public List<ProductResult> totalProductsForMonthlyDelivery(@Param("month") int month, @Param("year") int year);
	
	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure, round(sum(oi.quantity),2),count(d.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "group by p.id "
			+ "order by p.category")
	public List<ProductResult> totalProductsForAllDelivery();
	
	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure, round(sum(oi.quantity),2),count(d.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "where p.category.id=:categoryId "
			+ "group by p.id "
			+ "order by p.category")
	public List<ProductResult> totalProductsForAllDeliveryByCategory(@Param("categoryId") int categoryId);

	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure,  round(sum(oi.quantity),2), count(p.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join Client c on c.id = o.client.id "
			+ "where c.id =:clientId "
			+ "group by p.id "
			+ "order by count(p.id) desc")
	public List<ProductResult> findAllProductForClient(@Param("clientId") int clientId);
	
	@Query("select new com.pgciric.entity.ProductResult(p.id, p.name, p.price, p.category, p.unitOfMeasure, round(avg(oi.quantity),2), count(p.id)) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join Client c on c.id = o.client.id "
			+ "where c.id =:clientId "
			+ "group by p.id "
			+ "order by count(p.id) desc")
	public List<ProductResult> findAllProductForClientAverage(@Param("clientId") int clientId);
	
	@Query("select new com.pgciric.entity.MilkyProduct(p.id, p.name, oi.quantity, count(p.id), p.category) from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "where d.id =:deliveryId and p.category.id =:categoryId "
			+ "group by oi.quantity,p.name "
			+ "having count (oi.quantity) >=1 order by p.name ")
	public List<MilkyProduct> totalProductsForDeliveryByCategory(@Param("deliveryId") int deliveryId, @Param("categoryId") int categoryId);
	
	@Query(value="SELECT * FROM product "
				+ "LIMIT 4 "
				+ "OFFSET ?1 ", nativeQuery = true)
	public List<Product> paginationAndSortProductListByFour(@Param("page") int page);

	@Query("select count (*) from product")
	public Long totalPageCount();
	
	@Query("select count (*) from product p where p.category.id=:categoryId")
	public Long totalPageCountByCategory(@Param("categoryId") int categoryId);

	@Query("select p from product p where p.category.id=:categoryId")
	public Page<Product> findAllByCategory(Pageable paegeable,@Param("categoryId") int categoryId);
	
	@Query("select p from product p where concat (p.name, p.category, p.description, p.price) like %?1%")
	public Page<Product> searchAllProduct(@Param("serach") String search,Pageable pageable);

	@Query("select new com.pgciric.entity.ProductResultStats(p.id, p.name, monthname(d.date), year(d.date), p.category, p.unitOfMeasure, round(sum(oi.quantity),2), count(p.id)) "
			+ "from product p "
			+ "left join order_item oi on oi.product.id = p.id "
			+ "right join Order_orderitem ooi on ooi.orderItemId = oi.id "
			+ "right join Order o on o.id = ooi.orderId "
			+ "inner join delivery d on d.id = o.delivery.id "
			+ "where p.id =:id "
			+ "group by month(d.date) "
			+ "order by year(d.date) desc, month(d.date) desc")
	public List<ProductResultStats> getProductStatsByMonth(@Param("id") int id);

}
