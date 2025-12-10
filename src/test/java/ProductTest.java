import Interfaces.*;
import Piece.*;
import Primitive.*;
import Weight.*;
import org.example.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class ProductTest {

    @Test
    public void testPackaging() {
        Packaging box = new Packaging("Картонная коробка", 0.5);
        assertEquals("Картонная коробка", box.getName());
        assertEquals(0.5, box.getWeight(), 0.001);
    }

    @Test
    public void testBulkProduct() {
        BulkProduct rice = new BulkProduct("Рис", "Круглозерный рис высшего сорта");
        assertEquals("Рис", rice.getName());
        assertEquals("Круглозерный рис высшего сорта", rice.getDescription());
    }

    @Test
    public void testPieceProduct() {
        PieceProduct candy = new PieceProduct("Конфета", "Шоколадная конфета", 0.05);
        assertEquals("Конфета", candy.getName());
        assertEquals(0.05, candy.getPieceWeight(), 0.001);
    }

    @Test
    public void testPackagedBulkProduct() {
        BulkProduct sugar = new BulkProduct("Сахар", "Сахарный песок");
        Packaging bag = new Packaging("Полиэтиленовый пакет", 0.1);
        PackagedBulkProduct packagedSugar = new PackagedBulkProduct(sugar, 5.0, bag);

        assertEquals(5.0, packagedSugar.getNetWeight(), 0.001);
        assertEquals(5.1, packagedSugar.getGrossWeight(), 0.001);
        assertEquals("Сахар", packagedSugar.getName());
    }

    @Test
    public void testPackagedPieceProduct() {
        PieceProduct chocolate = new PieceProduct("Шоколадка", "Молочный шоколад", 0.1);
        Packaging box = new Packaging("Фирменная коробка", 0.2);
        PackagedPieceProduct packagedChocolate = new PackagedPieceProduct(chocolate, 10, box);

        assertEquals(10, packagedChocolate.getQuantity());
        assertEquals(1.0, packagedChocolate.getNetWeight(), 0.001);
        assertEquals(1.2, packagedChocolate.getGrossWeight(), 0.001);
        assertEquals("Шоколадка", packagedChocolate.getName());
    }

    @Test
    public void testProductBatch() {
        BulkProduct coffee = new BulkProduct("Кофе", "Арабика молотый");
        Packaging coffeeBag = new Packaging("Фольгированный пакет", 0.05);
        PackagedBulkProduct packagedCoffee = new PackagedBulkProduct(coffee, 0.5, coffeeBag);

        PieceProduct tea = new PieceProduct("Чай", "Зеленый чай в пакетиках", 0.002);
        Packaging teaBox = new Packaging("Картонная коробка", 0.1);
        PackagedPieceProduct packagedTea = new PackagedPieceProduct(tea, 100, teaBox);

        Weightable[] items = {packagedCoffee, packagedTea};
        ProductBatch batch = new ProductBatch("Бакалея", items);

        assertEquals(2, batch.getItems().length);
    }

    @Test
    public void testBeginStringFilter() {
        BeginStringFilter filter = new BeginStringFilter("Коф");
        assertTrue(filter.apply("Кофе"));
        assertFalse(filter.apply("Чай"));
    }

    @Test
    public void testEndStringFilter() {
        EndStringFilter filter = new EndStringFilter("ка");
        assertTrue(filter.apply("Шоколадка"));
        assertFalse(filter.apply("Конфета"));
    }

    @Test
    public void testContainsStringFilter() {
        ContainsStringFilter filter = new ContainsStringFilter("око");
        assertTrue(filter.apply("Шоколад"));
        assertFalse(filter.apply("Чай"));
    }

    @Test
    public void testProductService() {
        // Создаем товары
        BulkProduct coffee = new BulkProduct("Кофе", "Арабика");
        BulkProduct cola = new BulkProduct("Кола", "Напиток");
        PieceProduct cookie = new PieceProduct("Печенье", "Шоколадное", 0.05);


        Packaging bag = new Packaging("Пакет", 0.01);
        Packaging box = new Packaging("Коробка", 0.1);


        PackagedBulkProduct packagedCoffee = new PackagedBulkProduct(coffee, 1.0, bag);
        PackagedBulkProduct packagedCola = new PackagedBulkProduct(cola, 2.0, bag);
        PackagedPieceProduct packagedCookie = new PackagedPieceProduct(cookie, 20, box);


        Weightable[] items = {packagedCoffee, packagedCola, packagedCookie};
        ProductBatch batch = new ProductBatch("Напитки и сладости", items);

        ProductService service = new ProductService();
        BeginStringFilter filterK = new BeginStringFilter("К");

        int count = service.countByFilter(batch, filterK);
        assertEquals(2, count);
    }
}