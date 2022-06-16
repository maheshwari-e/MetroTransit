package repositorytest

import org.junit.jupiter.api.Test
import repository.RouteRepository
import repository.getRoute
import kotlin.test.assertEquals

class RouteRepositoryTest {
    private val fakeRouteRepository = object : RouteRepository{
        override fun getAllRoute(): Map<String, String> {
            return mapOf("METRO Blue Line" to "901",
                "Airport Shuttle" to  "906",
                "METRO A Line" to "921",
                "Northstar Commuter Rail" to "888",
                "Northstar Link" to "887",
                "Route 612" to "612",
                "Route 615" to "615"
            )
        }
    }

    @Test
    fun `Verify Route Id based on provide route name`(){
        assertEquals(
            expected = "612",
            actual = getRoute(
                name = "Route 612",
                repository = fakeRouteRepository
            )
        )
    }

    private val list = listOf("XYZ", "Route 617", "Route 612")

    @Test
    fun `Verify Route Id based on provide invalid route name`(){
        var index = 0
        assertEquals(
            expected = "612",
            actual = getRoute(
                name = "xyz",
                repository = fakeRouteRepository,
                readLine = { list[index++] }
            )
        )
    }

    @Test
    fun `Verify Route Id based without providing route name`(){
        var index = 0
        assertEquals(
            expected = "612",
            actual = getRoute(
                name = "",
                repository = fakeRouteRepository,
                readLine = { list[index++] }
            )
        )
    }

    @Test
    fun `Verify Route Id based provide route name as null`(){
        var index = 0
        assertEquals(
            expected = "612",
            actual = getRoute(
                name = null,
                repository = fakeRouteRepository,
                readLine = { list[index++] }
            )
        )
    }
}

