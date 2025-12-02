package com.myweddingplanner.back.mapper;

import com.myweddingplanner.back.dto.products.ImageProductDTO;
import com.myweddingplanner.back.dto.products.ListProductDTO;
import com.myweddingplanner.back.dto.products.ProductDTO;
import com.myweddingplanner.back.model.ImageProduct;
import com.myweddingplanner.back.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper{

    @Override
    public ProductDTO toProductDTO(Product entity) {

        ProductDTO dto = new ProductDTO();

        dto.setIdProduct(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLink(entity.getLink());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getProductType().toString());
        dto.setStartDate(entity.getStartDate());
        dto.setUpdateDate(entity.getUpdateDate());

        for (ImageProduct i : entity.getImages()){

            dto.getImages().add(toImageProductDTO(i));
        }

        return dto;
    }

    @Override
    public ImageProductDTO toImageProductDTO(ImageProduct entity) {

        ImageProductDTO dto = new ImageProductDTO();

        dto.setIdImage(entity.getId());
        dto.setLink(entity.getLink());
        dto.setImageType(entity.getImageType());

        return dto;
    }

    @Override
    public ListProductDTO toListProductDTO(List<Product> productList) {

        ListProductDTO dto = new ListProductDTO();

        for (Product p : productList){
            dto.getProducts().add(toProductDTO(p));
        }
        return dto;
    }
}
