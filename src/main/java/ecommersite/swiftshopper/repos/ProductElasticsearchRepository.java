package ecommersite.swiftshopper.repos;

import ecommersite.swiftshopper.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jdbc.repository.query.Query;

public interface ProductElasticsearchRepository  extends ElasticsearchRepository<Product,Integer>
{
    @Query("{\"bool\": {\"must\": [{\"match\": {\"category.name\": \"?0\"}}]}}")
    Page<Product> findByCategoryNameUsingCustomQuery(String name, Pageable pageable);
}