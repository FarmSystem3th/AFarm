package com.example.farmsystem.Service;

import com.example.farmsystem.Entity.Categories;
import com.example.farmsystem.Entity.Goods;
import com.example.farmsystem.Entity.Image;
import com.example.farmsystem.Repository.CategoriesRepository;
import com.example.farmsystem.Repository.GoodsRepository;
import com.example.farmsystem.Repository.ImageRepository;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final CategoriesRepository categoriesRepository;
    private final ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public GoodsService(GoodsRepository goodsRepository, CategoriesRepository categoriesRepository, ImageRepository imageRepository) {
        this.goodsRepository = goodsRepository;
        this.categoriesRepository = categoriesRepository;
        this.imageRepository = imageRepository;
    }

    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    public Goods createGoodsWithImages(Goods goods, Long categoryId, List<MultipartFile> files) throws IOException {
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        goods.setCategory(category);

        // 상품 저장
        Goods savedGoods = goodsRepository.save(goods);

        // 이미지 파일 저장 및 Image 엔티티 생성
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + "/" + fileName;

            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            Image image = new Image();
            image.setFileName(fileName);
            image.setFilePath(filePath);
            image.setGoods(savedGoods);
            images.add(image);
        }

        imageRepository.saveAll(images);  // 이미지 엔티티 저장
        return savedGoods;
    }

    public Goods findGoodsById(Long id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Goods not found"));
    }
}
