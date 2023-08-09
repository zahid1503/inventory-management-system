package com.pharmIT.inventoryManager.repository;

import com.pharmIT.inventoryManager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
