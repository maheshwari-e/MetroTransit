package repositorytest

import org.junit.jupiter.api.Test
import repository.StopRepository
import repository.getStop
import kotlin.test.assertEquals

class StopRepositoryTest {
    private val fakeStopRepository = object : StopRepository {

        override fun getAllStop(routeId: String, directionId: String): Map<String, String> {
            return mapOf(
                "Excelsior Blvd and Quentin Ave" to "EXQU",
                "Shady Oak Road and Excelsior Blvd" to "SHEX",
                "Smetana Rd and 11th Ave" to  "11SM",
                "Opportunity Partners" to "OPPA",
                "Hennepin Ave and Uptown Transit Station - Gate B" to "HEUP",
                "Excelsior Blvd and Methodist Hospital" to "MEEX"
            )
        }
    }

    @Test
    fun `Verify Stop Id based on provide stop name`() {
        assertEquals(
            expected = "HEUP",
            actual = getStop(
                routeId = "612",
                directionId = "0",
                name = "Hennepin Ave and Uptown Transit Station - Gate B",
                repository = fakeStopRepository
            )
        )
    }

    private val list = listOf("XYZ", "Uptown", "Excelsior Blvd and Quentin Ave")

    @Test
    fun `Verify Stop Id based on provide invalid stop name`() {
        var index = 0
        assertEquals(
            expected = "EXQU",
            actual = getStop(routeId = "612",
                directionId = "0",
                name = "xyz",
                repository = fakeStopRepository,
                readLine = { list[index++] }
            )
        )
    }

    @Test
    fun `Verify Stop Id based without providing stop name`() {
        var index = 0
        assertEquals(
            expected = "EXQU",
            actual = getStop(
                routeId = "612",
                directionId = "0",
                name = "",
                repository = fakeStopRepository,
                readLine = { list[index++] }
            )
        )
    }

    @Test
    fun `Verify Stop Id based provide stop name as null`() {
        var index = 0
        assertEquals(
            expected = "EXQU",
            actual = getStop(
                routeId = "612",
                directionId = "0",
                name = null,
                repository = fakeStopRepository,
                readLine = { list[index++] }
            )
        )
    }
}

