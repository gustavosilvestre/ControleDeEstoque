package br.com.cast.turmaformacao.controledeestoque.model.service;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.http.ProductService;
import br.com.cast.turmaformacao.controledeestoque.persistence.ProdutoRepository;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class ProductBusinessService {

    private ProductBusinessService() {
        super();
    }

    public static void sincronized() {

        List<Product> listaWeb = ProductService.getAll();

        for (Product productWeb : listaWeb) {

            Long id_local = ProductBusinessService.getIdByWebId(productWeb.getId_web());
            productWeb.setFlag(true);

            if (id_local == null) {

                save(productWeb);

            } else {

                productWeb.setId(id_local);

                Product produtoLocal = ProductBusinessService.getById(id_local);

                if (checkLastModified(produtoLocal, productWeb)) {
                    save(productWeb);
                }
            }
        }
    }

    private static boolean checkLastModified(Product web, Product local) {

        if (web.getData() > local.getData()) {
            return true;
        }

        return false;

    }

    public static Product getById(Long id) {
        return ProdutoRepository.getById(id);
    }

    public static Long getIdByWebId(Long web_id) {

        return ProdutoRepository.getIdByWebId(web_id);
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
