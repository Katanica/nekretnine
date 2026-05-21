package com.example.backend.Specification;

import com.example.backend.DTO.AdvertFilterDto;
import com.example.backend.Entity.Advert;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.*;
import java.math.BigDecimal;

public class AdvertSpecification {
    public static Specification<Advert> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isEmpty()) {
                return null;                    // nema uslova
            }
            return cb.like(
                    cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }
}
