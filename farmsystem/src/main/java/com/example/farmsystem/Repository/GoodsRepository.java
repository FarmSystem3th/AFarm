package com.example.farmsystem.Repository;

import com.example.farmsystem.Entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}

