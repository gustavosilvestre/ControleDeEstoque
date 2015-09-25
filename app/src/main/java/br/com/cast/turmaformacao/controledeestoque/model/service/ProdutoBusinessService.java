package br.com.cast.turmaformacao.controledeestoque.model.service;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.persistence.ProdutoRepository;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class ProdutoBusinessService {

    private ProdutoBusinessService() {
        super();
    }

    public static void save(Produto produto) {

        ProdutoRepository.save(produto);
    }

    public static List<Produto> findAll() {

        return ProdutoRepository.findAll();
    }

    public static void delete(Produto produto) {
        ProdutoRepository.delete(produto.getId());
    }
}
