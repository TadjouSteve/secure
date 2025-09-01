package com.ste.secure.others;

import org.springframework.context.ApplicationEvent;

import com.ste.secure.model.Produit;

public class ProduitCreatedEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Produit produit;

    public ProduitCreatedEvent(Object source, Produit produit) {
        super(source);
        this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }
}
