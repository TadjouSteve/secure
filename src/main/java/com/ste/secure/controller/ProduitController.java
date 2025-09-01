package com.ste.secure.controller;

import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ste.secure.model.Produit;
import com.ste.secure.service.ProduitService;

@RestController
@RequestMapping("/produit")
public class ProduitController {
	private final ProduitService produitService;

    // Injection par constructeur (recommandé au lieu de @Autowired)
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // POST - Créer un produit
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitService.createOrUpdate(produit);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }

    // GET - Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduitById(@PathVariable Long id) {
        try {
            Produit produit = produitService.getbyId(id);
            return ResponseEntity.ok(produit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET - Liste de tous les produits
    @GetMapping("/list")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getListProduit();
        return ResponseEntity.ok(produits);
    }

    // PUT - Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        try {
            // Vérifie que l'ID dans le path correspond à l'ID du produit
            if (!id.equals(produit.getId())) {
                return ResponseEntity.badRequest().body("L'ID dans l'URL ne correspond pas à l'ID du produit");
            }
            
            Produit updatedProduit = produitService.createOrUpdate(produit);
            return ResponseEntity.ok(updatedProduit);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la mise à jour du produit");
        }
    }

    // DELETE - Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable Long id) {
        try {
            // D'abord vérifier que le produit existe
            produitService.getbyId(id);
            produitService.deleteProduit(id); // Vous devrez ajouter cette méthode dans votre service
            return ResponseEntity.ok("Produit supprimé avec succès");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AttributeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
