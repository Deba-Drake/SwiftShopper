package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

//@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<Product, Integer> {

    Page<Product> findByProductCategory(String name, Pageable pageable);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"productCategory\": \"?0\"}}]}}")
//    List<Product> findByProductCategoryUsingCustomQuery(String category);
}