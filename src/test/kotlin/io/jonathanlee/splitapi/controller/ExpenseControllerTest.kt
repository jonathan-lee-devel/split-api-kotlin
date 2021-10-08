package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.model.Property
import io.jonathanlee.splitapi.model.auth.User
import io.jonathanlee.splitapi.repository.PropertyRepository
import io.jonathanlee.splitapi.service.ExpenseService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@WebMvcTest(controllers = [ExpenseController::class])
class ExpenseControllerTest {

    @MockBean
    private lateinit var expenseService: ExpenseService

    @Mock
    private lateinit var propertyRepository: PropertyRepository

    @Autowired
    private lateinit var mvc: MockMvc

    @BeforeEach
    fun beforeEach() {
        `when`(propertyRepository.findByPropertyId(anyString())).thenReturn(
            Property(
                0L,
                "cc90dbd3-1fa1-45cf-9771-3ce4e56f7e5f",
                Date(),
                "Test Name",
                "Test address",
                User(
                    0L, "cc90dbd3-1fa1-45cf-9771-3ce4e56f7e5f", "test@mail.com",
                    "password", true, Date(), null, null
                )
            )
        )
    }

    @Test
    fun whenGetRequest_AndNoExpense_thenReturn200() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/expense")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun whenGetRequestById_AndNoExpense_thenReturn404() {
        mvc.perform(
            MockMvcRequestBuilders
                .get("/expense/id/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun whenPost_AndExpense_thenReturn201() {
        mvc.perform(
            MockMvcRequestBuilders
                .post("/expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n" +
                            "    \"amount\":33.33,\n" +
                            "    \"frequency\":\"ONCE\",\n" +
                            "    \"is_active\":true,\n" +
                            "    \"start_date\":\"2021-09-25\",\n" +
                            "    \"property_id\":\"cc90dbd3-1fa1-45cf-9771-3ce4e56f7e5f\"\n" +
                            "}"
                )
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

}
