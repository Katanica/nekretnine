package com.example.backend.Repository;

import com.example.backend.DTO.AdvertDto;
import com.example.backend.Entity.Advert;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert>{

    List<Advert> findByProfileId(Long profileId);
}