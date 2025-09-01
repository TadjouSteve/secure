package com.ste.secure.service;

import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ste.secure.model.Produit;
import com.ste.secure.others.ProduitCreatedEvent;
import com.ste.secure.repository.ProduitRepository;

@Service
public class ProduitService {
	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
    private  ApplicationEventPublisher eventPublisher;

	
	
	public Produit createOrUpdate(Produit produit) {
		produit=produitRepository.save(produit);
		
		eventPublisher.publishEvent(new ProduitCreatedEvent(this, produit));
		System.out.println("L'evenement a ete lancer...");
		return produit;
	}
	
	public Produit getbyId(Long id) {
		
		Produit produit=null;
		
		if (id==null || id<=0) {
			throw new IllegalArgumentException("L'ID de  du produit est invalide");
		}
		
		try {
			produit= produitRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Le produit demande n'a pu etre retourne erreur interne"));
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produit;
	}
	
	public List<Produit> getListProduit() {
		return produitRepository.findAll();
	}
	
	public void deleteProduit(Long id) throws AttributeNotFoundException {
	    if (id == null || id <= 0) {
	        throw new IllegalArgumentException("L'ID du produit est invalide");
	    }
	    
	    Produit produit = getbyId(id); // Vérifie que le produit existe
	    produitRepository.delete(produit);
	}
	
}
