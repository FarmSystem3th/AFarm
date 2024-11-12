package com.example.farmsystem.Controller;

import com.example.farmsystem.Entity.Goods;
import com.example.farmsystem.Service.GoodsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    // 모든 상품 조회
    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    // 상품 등록 - 이미지 파일 포함
    @PostMapping
    public Goods createGoods(
            @RequestPart("goods") Goods goods,
            @RequestParam Long categoryId,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        return goodsService.createGoodsWithImages(goods, categoryId, files);
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    public Goods getGoodsById(@PathVariable Long id) {
        return goodsService.findGoodsById(id);
    }
}
