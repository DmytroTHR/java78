
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        ProductService service = context.getBean(ProductService.class);

        List<Product> items = new ArrayList<>();
        items.add(new Product("TV", 12));
        items.add(new Product("Phone", 10));
        items.add(new Product("Laptop", 15));

        service.getFinalPrice(items);
    }
}
