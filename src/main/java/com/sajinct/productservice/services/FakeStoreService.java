package com.sajinct.productservice.services;

import com.sajinct.productservice.dtos.FakeStoreProductDto;
import com.sajinct.productservice.models.Category;
import com.sajinct.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreService implements ProductService{

    RestTemplate restTemplate;

    FakeStoreService(RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(long id) {
       FakeStoreProductDto fakeStoreProductDto = this.restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
       return convertFakestoreDtotoProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setId(0);
        category.setTitle(fakeStoreProductDto.getCategory());

        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreDto(Product product) {
        if (product == null) {
            return null;
        }
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        }

        return fakeStoreProductDto;
    }


    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto fakeStoreProductDto = this.restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        //return convertFakeStoreDtoToProduct(fakeStoreProductDto);
        return List.of();
    }

    @Override
    public Product updateProduct(long id,Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        fakeStoreProductDto =
                restTemplate
                        .put("https://fakestoreapi.com/products"+id, fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        fakeStoreProductDto =
                restTemplate
                        .postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(long id,Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        fakeStoreProductDto =
                restTemplate
                        .patchForObject("https://fakestoreapi.com/products"+id, fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public void deleteProduct(long id) {

    }
}
