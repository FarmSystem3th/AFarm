package com.example.farmsystem.Repository;

import com.example.farmsystem.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
