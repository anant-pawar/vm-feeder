import com.baidu.unbiz.fluentvalidator.Result;
import com.visualmeta.feeder.error.ShopFeedValidationException;
import com.visualmeta.feeder.model.Message;
import com.visualmeta.feeder.model.Product;
import com.visualmeta.feeder.model.ProductField;
import com.visualmeta.feeder.repository.ShopFeedRepository;
import com.visualmeta.feeder.service.ShopFeedService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.UUID;


public class ShopFeedTest {

    private ShopFeedService shopFeedService;
    private ShopFeedRepository shopFeedRepository;

    @Before
    public void setup() {
        this.shopFeedRepository = Mockito.mock(ShopFeedRepository.class);
        this.shopFeedService = new ShopFeedService(shopFeedRepository);
    }

    @Test()
    public void createProduct_FailedFiledValidation() {
        Result validationResult = new Result();
        validationResult.setErrors(
                Arrays.asList(
                        ProductField.ID.toString() + " cannot be empty"
                        , ProductField.NAME.toString() + " cannot be empty"));

        Assert
                .assertEquals(
                        shopFeedService.createProduct(new Product())
                        , new Message(Message.StatusCode.FAILED, validationResult.toString()));
    }

    @Test
    public void createProduct() throws ShopFeedValidationException {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Jacket");

        Assert
                .assertEquals(
                        shopFeedService.createProduct(product)
                        , new Message(Message.StatusCode.OK));
    }
}
