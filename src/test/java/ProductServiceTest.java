import com.codecool.shop.dao.*;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


class ProductServiceTest {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao productSupplierDao;
    private ShoppingCartDao shoppingCart;

    private OrderDao orderDao;


    @BeforeEach
    void setUp() {
        productDao = mock(ProductDao.class);
        productCategoryDao = mock(ProductCategoryDao.class);
        productSupplierDao = mock(SupplierDao.class);
        shoppingCart = mock(ShoppingCartDao.class);
        orderDao = mock(OrderDao.class);
        ProductService productService = new ProductService(productDao,productCategoryDao,productSupplierDao,shoppingCart);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getProductCategory_productCategoryIsNull_returnNull() {

    }

    @Test
    void getProductsForCategory() {
    }

    @Test
    void getProductsForSupplier() {
    }

    @Test
    void getProductsForCategoryAndSupplier() {
    }

    @Test
    void getAllCategory() {
    }

    @Test
    void getAllCartItems() {
    }

    @Test
    void getShoppingCart() {
    }

    @Test
    void getAllSuppliers() {
    }

    @Test
    void getOrderId() {
    }

    @Test
    void getAllProducts() {
    }
}