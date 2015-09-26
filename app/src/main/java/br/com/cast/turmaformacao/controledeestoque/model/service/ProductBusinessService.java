package br.com.cast.turmaformacao.controledeestoque.model.service;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.persistence.ProdutoRepository;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class ProductBusinessService {

    private ProductBusinessService() {
        super();
    }

    public static void save(Product product) {

        ProdutoRepository.save(product);
    }

    public static List<Product> findAll() {

        return ProdutoRepository.findAll();
    }

    public static void delete(Product product) {
        ProdutoRepository.delete(product.getId());
    }
}
