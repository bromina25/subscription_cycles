package com.lufthansa.subscriptions.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lufthansa.subscriptions.constant.enums.ActivationState;
import com.lufthansa.subscriptions.dto.customer.CustomerDto;
import com.lufthansa.subscriptions.entity.Customer;
import com.lufthansa.subscriptions.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CustomerController Integration Test")
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    private static Long createdCustomerId;

    @BeforeEach
    void setUp() {
        // Clean up before each test to ensure isolation
    }

    @AfterEach
    void tearDown() {
        // Cleanup after tests if needed
    }

    @Test
    @DisplayName("Should retrieve customer details successfully")
    void testGetCustomerDetails_Success() throws Exception {
        Customer customer = Customer.builder()
                .name("Romina")
                .surname("Bishani")
                .email("romina.bishani@example.com")
                .phoneNumber("+9876543210")
                .activationState(ActivationState.ACTIVE)
                .build();
        Customer savedCustomer = customerRepository.save(customer);

        // Act & Assert
        mockMvc.perform(get("/api/customer/{id}", savedCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCustomer.getId()))
                .andExpect(jsonPath("$.name").value("Romina"))
                .andExpect(jsonPath("$.surname").value("Bishani"))
                .andExpect(jsonPath("$.email").value("romina.bishani@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+9876543210"))
                .andExpect(jsonPath("$.activationState").value("ACTIVE"));
    }

    @Test
    @DisplayName("Should return 404 when customer not found")
    void testGetCustomerDetails_NotFound() throws Exception {
        Long nonExistentId = 999999L;

        // Act & Assert
        mockMvc.perform(get("/api/customer/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create customer and then retrieve its details")
    void testCreateAndGetCustomer_Integration() throws Exception {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Romina");
        customerDto.setSurname("Bishani");
        customerDto.setEmail("romina.bishani@example.com");
        customerDto.setPhoneNumber("+5544332211");

        MvcResult createResult = mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        String createResponse = createResult.getResponse().getContentAsString();
        Long customerId = objectMapper.readTree(createResponse).get("id").asLong();

        mockMvc.perform(get("/api/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.name").value("Romina"))
                .andExpect(jsonPath("$.surname").value("Bishani"))
                .andExpect(jsonPath("$.email").value("romina.bishani@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("+5544332211"))
                .andExpect(jsonPath("$.activationState").exists());
    }
}

