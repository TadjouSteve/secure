package com.ste.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ste.secure.model.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
