package com.ste.secure.others;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ste.secure.model.Produit;

@Component
public class ProduitEventListener {
	@Async // Pour un traitement asynchrone
    @EventListener
    public void handleProduitCreatedEvent(ProduitCreatedEvent event) {
        Produit produit = event.getProduit();
        //wait(1000);
        System.out.println("Nouveau produit créé: " + produit.getName() + 
                         " (ID: " + produit.getId() + ")");
        
        // Ici vous pouvez ajouter votre logique métier
        // Ex: Envoyer une notification, mettre à jour un index, etc.
    }
}
