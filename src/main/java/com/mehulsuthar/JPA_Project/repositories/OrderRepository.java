package com.mehulsuthar.JPA_Project.repositories;

import com.mehulsuthar.JPA_Project.models.embedded.Order;
import com.mehulsuthar.JPA_Project.models.embedded.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

    // Find orders by username
    List<Order> findByIdUsername(String username);

    // Find orders by date range
    List<Order> findByIdOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find orders after specific date
    List<Order> findByIdOrderDateAfter(LocalDateTime date);

    // Find orders before specific date
    List<Order> findByIdOrderDateBefore(LocalDateTime date);

    // Find orders by order info containing text
    List<Order> findByOrderInfoContainingIgnoreCase(String orderInfo);

    // Find orders by username and date range
    List<Order> findByIdUsernameAndIdOrderDateBetween(
            String username, 
            LocalDateTime startDate, 
            LocalDateTime endDate
    );

    // Find orders by address street name
    List<Order> findByAddressStreetNameContainingIgnoreCase(String streetName);

    // Find orders by address house number
    List<Order> findByAddressHouseNo(String houseNo);

    // Custom query to find orders with full address details
    @Query("SELECT o FROM Order o WHERE o.address.streetName = :streetName AND o.address.houseNo = :houseNo")
    List<Order> findByFullAddress(@Param("streetName") String streetName, @Param("houseNo") String houseNo);

    // Find recent orders (last N days)
    @Query("SELECT o FROM Order o WHERE o.id.orderDate >= :date ORDER BY o.id.orderDate DESC")
    List<Order> findRecentOrders(@Param("date") LocalDateTime date);

    // Count orders by username
    long countByIdUsername(String username);

    // Find orders by partial username match
    List<Order> findByIdUsernameContainingIgnoreCase(String usernamePart);

    // Find orders ordered by date (most recent first)
    List<Order> findAllByOrderByIdOrderDateDesc();

    // Custom query to find orders by username with address info
    @Query("SELECT o FROM Order o WHERE o.id.username = :username AND o.address IS NOT NULL")
    List<Order> findOrdersWithAddressByUsername(@Param("username") String username);
}
