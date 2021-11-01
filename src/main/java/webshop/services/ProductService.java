package webshop.services;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import webshop.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    // TODO: clean-up code (create separate methods for predicates list, countQuery and sort)
    public Page<Product> findAllWithFilters(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        criteriaQuery.distinct(true);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        Join<Product, Animal> joinAnimal = productRoot.join("animal", JoinType.INNER);
        Join<Product, Category> joinCategory = productRoot.join("category", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if(productSearchCriteria.getAnimalName() != null) {
            predicates.add(criteriaBuilder.equal(productRoot.get("animal").get("name"), productSearchCriteria.getAnimalName()));
        }

        if(productSearchCriteria.getCategories() != null) {
            In<String> inCategories = criteriaBuilder.in(productRoot.get("category").get("name"));
            for(String category : productSearchCriteria.getCategories()) {
                inCategories.value(category);
            }
            predicates.add(inCategories);
        }

        if(productSearchCriteria.getBrands() != null) {
            CriteriaBuilder.In<String> inBrands = criteriaBuilder.in(productRoot.get("brand"));
            for(String category : productSearchCriteria.getBrands()) {
                inBrands.value(category);
            }
            predicates.add(inBrands);
        }

        Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.select(productRoot).where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(productPage.getPageNumber() * productPage.getPageSize());
        typedQuery.setMaxResults(productPage.getPageSize());

        if(productSearchCriteria.getSortDirection() != null) {
            if(productSearchCriteria.getSortDirection() == Sort.Direction.ASC)
            productPage.setSortDirection(Sort.Direction.ASC);
        } else {
            productPage.setSortDirection(Sort.Direction.DESC);
        }

        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortby());
        Pageable pageable = PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        long productsCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(typedQuery.getResultList(), pageable, productsCount);
    }
}
