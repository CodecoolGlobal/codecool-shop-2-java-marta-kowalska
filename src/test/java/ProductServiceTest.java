import com.codecool.shop.dao.*;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.product.ProductCategory;
import com.codecool.shop.model.product.Supplier;
import com.codecool.shop.model.shoppingCart.ShoppingCart;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceTest {
    private ProductDao productDaoMock;
    private ProductCategoryDao productCategoryDaoMock;
    private SupplierDao productSupplierDaoMock;
    private ShoppingCartDao shoppingCartMock;
    ProductService productService;
    private OrderDao orderDaoMock;
    private List<Product> emptyProductList = new ArrayList<>();

    ProductCategory dummyCategory = new ProductCategory("dummyCategory");
    Supplier dummySupplier = new Supplier("dummySupplier");
    Product dummyProduct = new Product("dummyProduct", 150, "USD", "hoodie.jpg", "A must have for every real hacker", dummyCategory, dummySupplier);
    ShoppingCart dummyShoppingCart = new ShoppingCart();

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

    @Test
    void getProductCategory_givenProductCategory_WhenProductCategoryIdExists_ThenReturnCategory() {
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummyCategory);
       var result =  productService.getProductCategory(1);
       assertEquals(dummyCategory, result);
    }

    @Test
    void getProductCategory_givenProductCategory_WhenProductCategoryIdNotExists_ThenReturnNull() {
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
       var result =  productService.getProductCategory(1);
        assertNull(result);
    }

    @Test
    void getProductsForCategory_givenProductCategory_WhenProductCategoryIdExists_ThenReturnListOfProducts() {
        var expected = List.of(dummyProduct,dummyProduct);
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummyCategory);
        when(productDaoMock
                .getBy(dummyCategory))
                .thenReturn(List.of(dummyProduct,dummyProduct));
      var result =  productService.getProductsForCategory(1);
      assertEquals(expected, result);
    }

    @Test
    void getProductsForCategory_givenNull_WhenProductCategoryIdNotExists_ThenReturnNull() {
        var expected = new ArrayList<>();
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productDaoMock
                .getBy(dummyCategory))
                .thenReturn(List.of(dummyProduct,dummyProduct));
      var result =  productService.getProductsForCategory(1);
        assertEquals(expected,result);
    }

    @Test
    void getProductsForSupplier_givenSupplierId_WhenSupplierIdExists_ThenReturnListOfProducts() {
        var expected = List.of(dummyProduct,dummyProduct);
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummySupplier);
        when(productDaoMock
                .getBy(dummySupplier))
                .thenReturn(List.of(dummyProduct,dummyProduct));
        var result =  productService.getProductsForSupplier(1);
        assertEquals(expected, result);

    }
    @Test
    void getProductsForSupplier_givenSupplierId_WhenSupplierIdNotExists_ThenReturnNull() {
        var expected = List.of();
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productDaoMock
                .getBy(dummySupplier))
                .thenReturn(List.of(dummyProduct,dummyProduct));
        var result =  productService.getProductsForSupplier(1);
        assertEquals(expected, result);

    }

    @Test
    void getProductsForCategoryAndSupplier_givenSupplierIdAndCategoryId_WhenBothExists_ThenReturnProducts() {
        var expected = List.of(dummyProduct,dummyProduct);
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummySupplier);
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummyCategory);
        when(productDaoMock
                .getBy(dummyCategory,dummySupplier))
                .thenReturn(List.of(dummyProduct,dummyProduct));
        var result =  productService.getProductsForCategoryAndSupplier(1,1);
        assertEquals(expected, result);
    }
    @Test
    void getProductsForCategoryAndSupplier_givenSupplierIdAndCategoryId_WhenSupplierIdNotExists_ThenReturnProducts() {
        var expected = List.of(dummyProduct,dummyProduct);
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummyCategory);
        when(productDaoMock
                .getBy(dummyCategory,null))
                .thenReturn(List.of(dummyProduct,dummyProduct));
        var result =  productService.getProductsForCategoryAndSupplier(1,1);
        assertEquals(expected, result);
    }
    @Test
    void getProductsForCategoryAndSupplier_givenSupplierIdAndCategoryId_WhenCategoryIdNotExists_ThenReturnProducts(){
        var expected = List.of(dummyProduct,dummyProduct);
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(dummySupplier);
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productDaoMock
                .getBy(null,dummySupplier))
                .thenReturn(List.of(dummyProduct,dummyProduct));
        var result =  productService.getProductsForCategoryAndSupplier(1,1);
        assertEquals(expected, result);
    }

    @Test
    void getProductsForCategoryAndSupplier_givenSupplierIdAndCategoryId_WhenNeitherIdExists_ThenReturnNull() {
        var expected = List.of();
        when(productSupplierDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productCategoryDaoMock
                .find(Mockito.anyInt()))
                .thenReturn(null);
        when(productDaoMock
                .getBy(dummyCategory,dummySupplier))
                .thenReturn(List.of());
        var result =  productService.getProductsForCategoryAndSupplier(1,1);
        assertEquals(expected, result);
    }


    @Test
    void getAllCategory_WhenHaveProducts_ThenReturnListOfProducts() {
        var expected = List.of(dummyCategory,dummyCategory);
        when(productCategoryDaoMock
                .getAll())
                .thenReturn(List.of(dummyCategory,dummyCategory));
        var result =  productService.getAllCategory();
        assertEquals(expected, result);
    }

    @Test
    void getAllCartItems_WhenHaveProducts_ThenReturnsHashMapWithProducts() {
        HashMap<Product, Integer> expected = new HashMap<>();
        expected.put(dummyProduct, 5);

        when(shoppingCartMock
            .getAll())
            .thenReturn(expected);

        var result = productService.getAllCartItems();
        assertEquals(expected, result);
    }

    @Test
    void getAllCartItems_WhenNoProducts_ThenReturnsEmptyHashMap() {
        HashMap<Product, Integer> expected = new HashMap<>();

        when(shoppingCartMock
            .getAll())
            .thenReturn(new HashMap<>());

        var result = productService.getAllCartItems();
        assertEquals(expected, result);
    }

    @Test
    void getAllCartItems_nullProvided_returnsNull() {
         when(shoppingCartMock
            .getAll())
            .thenReturn(null);

        var result = productService.getAllCartItems();
        assertNull(result);
    }

    @Test
    void getShoppingCart_WhenShoppingCartExists_ThenReturnsShoppingCart() {
        when(shoppingCartMock
            .getCart())
            .thenReturn(dummyShoppingCart);

        var expected = dummyShoppingCart;
        var result = productService.getShoppingCart();

        assertEquals(expected, result);
    }

    @Test
    void getShoppingCart_WhenNoShoppingCartExists_ThenReturnsNull() {
        when(shoppingCartMock
            .getCart())
            .thenReturn(null);

        var result = productService.getShoppingCart();

        assertNull(result);
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