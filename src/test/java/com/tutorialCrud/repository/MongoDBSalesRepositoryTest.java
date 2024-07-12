package com.tutorialCrud.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.tutorialCrud.model.Customer;
import com.tutorialCrud.model.Item;
import com.tutorialCrud.model.Sales;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class MongoDBSalesRepositoryTest {
    @Container
    @ServiceConnection
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.0"));
    private static MongoClient mongoClient;

    @Autowired
    private SalesRepository salesRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.data.mongodb.database", () -> "testdb");
    }

    @BeforeAll
    public static void setUpAll() {
        String mongoUri = mongoDBContainer.getConnectionString();
        mongoClient = MongoClients.create(mongoUri);
    }

    @BeforeEach
    public void setUp() {
        salesRepository.save(createMockSales());
    }

    private Sales createMockSales() {
        Item item1 = new Item("Item1", Arrays.asList("Tag1", "Tag2"), new BigDecimal("19.99"), 2);
        Item item2 = new Item("Item2", Arrays.asList("Tag3", "Tag4"), new BigDecimal("29.99"), 1);
        List<Item> items = Arrays.asList(item1, item2);

        Customer customer = new Customer("Male", 30, "customer@example.com", 5);

        return new Sales(new ObjectId(), new Date(), items, "Store1", customer, true, "Online");
    }

    @Test
    public void testFindAll() {
        List<Sales> salesList = salesRepository.findAll();
        assertThat(salesList).isNotEmpty();
        assertThat(salesList.size()).isEqualTo(1);
        Sales sales = salesList.get(0);
        assertThat(sales.getStoreLocation()).isEqualTo("Store1");
        assertThat(sales.getCustomer().getEmail()).isEqualTo("customer@example.com");
        assertThat(sales.getItems()).hasSize(2);
    }

    @AfterAll
    public static void cleanup() {
//        mongoClient.close();
    }
}
