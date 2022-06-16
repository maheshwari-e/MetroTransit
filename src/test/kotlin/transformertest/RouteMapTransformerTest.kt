package transformertest

import model.Route
import org.junit.jupiter.api.Test
import transformer.RouteMapTransformer
import kotlin.test.assertEquals

class RouteMapTransformerTest {

    private val routes = mutableListOf(
        Route("901", "METRO Blue Line" ),
        Route("906","Airport Shuttle"),
        Route("921", "METRO A Line"),
        Route("888", "Northstar Commuter Rail"),
        Route("887", "Northstar Link"),
        Route("612", "Route 612"),
        Route("615", "Route 615"))

    private val expected = mapOf("METRO Blue Line" to "901",
        "Airport Shuttle" to  "906",
        "METRO A Line" to "921",
        "Northstar Commuter Rail" to "888",
        "Northstar Link" to "887",
        "Route 612" to "612",
        "Route 615" to "615"
    )

    @Test
    fun `Verify transformation from list of route to map`(){
        assertEquals(
            expected = expected,
            actual = RouteMapTransformer.invoke(routes)
        )
    }

    @Test
    fun `Filter out null while conversion from list of route to map `(){
        routes.apply {
            add(Route(null, null))
            add(Route("1", null))
            add(Route(null, "Route 2"))
            add(Route("", null))
            add(Route(null, ""))
        }

        assertEquals(
            expected = expected,
            actual = RouteMapTransformer.invoke(routes)
        )
    }

    @Test
    fun `Filter out empty string value while conversion from list of route to map `(){
        routes.apply {
            add(Route("", ""))
            add(Route("5", ""))
            add(Route("", "Route 6"))
        }

        assertEquals(
            expected = expected,
            actual = RouteMapTransformer.invoke(routes)
        )
    }

    @Test
    fun `Verify transformation for empty list`(){
        assertEquals(
            expected = emptyMap(),
            actual = RouteMapTransformer.invoke(listOf())
        )
    }

    @Test
    fun `Verify transformation for null input value`(){
        assertEquals(
            expected = emptyMap(),
            actual = RouteMapTransformer.invoke(null)
        )
    }
}
