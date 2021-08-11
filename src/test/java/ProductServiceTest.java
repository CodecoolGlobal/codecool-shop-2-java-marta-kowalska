import com.codecool.shop.dao.*;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceTest {
    private ProductDao productDaoMock;
    private ProductCategoryDao productCategoryDaoMock;
    private SupplierDao productSupplierDaoMock;
    private ShoppingCartDao shoppingCartMock;
    ProductService productService;
    private OrderDao orderDaoMock;

    ProductCategory dummyCategory = new ProductCategory("dummy");


    @BeforeEach
    void setUp() {
        productDaoMock = mock(ProductDao.class);
        productCategoryDaoMock = mock(ProductCategoryDao.class);
        productSupplierDaoMock = mock(SupplierDao.class);
        shoppingCartMock = mock(ShoppingCartDao.class);
        orderDaoMock = mock(OrderDao.class);
        productService = new ProductService(productDaoMock,productCategoryDaoMock,productSupplierDaoMock,shoppingCartMock);
    }

    @AfterEach
    void tearDown() {
    }

    //TODO Így kéne tesztelni vagy most hogy? Confuused...:D
    @Test
    void givenProductCategory_WhenProductCategoryIdExists_ThReturnCategory() {
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummyCategory);
       var result =  productService.getProductCategory(1);
       assertEquals(dummyCategory, result);
    }

    @Test
    void givenProductCategory_WhenProductCategoryIdNotExists_ThReturnNull() {
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
       var result =  productService.getProductCategory(1);
        assertNull(result);
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