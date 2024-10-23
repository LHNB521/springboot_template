package com.lihao.springboottemplate.specification;

import com.lihao.springboottemplate.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<UserEntity> filterByCriteria(String username, String name, String role, Integer enabled, Integer locked) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (username != null && !username.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            if (role != null && !role.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("role"), role));
            }

            if (enabled != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("enabled"), enabled));
            }

            if (locked != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("locked"), locked));
            }

            return predicate;
        };
    }
}
