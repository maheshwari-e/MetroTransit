package transformertest

import model.Stop
import org.junit.jupiter.api.Test
import transformer.StopMapTransformer
import kotlin.test.assertEquals

class StopMapTransformerTest {

    private val stops = mutableListOf(
        Stop("EXQU","Excelsior Blvd and Quentin Ave"),
        Stop("SHEX","Shady Oak Road and Excelsior Blvd"),
        Stop("11SM", "Smetana Rd and 11th Ave"),
        Stop("OPPA", "Opportunity Partners"),
        Stop("HEUP", "Hennepin Ave and Uptown Transit Station - Gate B"),
        Stop("MEEX", "Excelsior Blvd and Quentin Ave")
    )

    private val expected = mapOf(
        "Excelsior Blvd and Quentin Ave" to "EXQU",
        "Shady Oak Road and Excelsior Blvd" to "SHEX",
        "Smetana Rd and 11th Ave" to  "11SM",
        "Opportunity Partners" to "OPPA",
        "Hennepin Ave and Uptown Transit Station - Gate B" to "HEUP",
        "Excelsior Blvd and Quentin Ave" to "MEEX"
    )

    @Test
    fun `Verify transformation from list of stop to map`() {
        assertEquals(
            expected = expected,
            actual = StopMapTransformer.invoke(stops)
        )
    }

    @Test
    fun `Filter out null while conversion from list of stop to map `() {
        stops.apply {
            add(Stop(null, null))
            add(Stop("SHKT", null))
            add(Stop(null, "K-Tel Dr and Shady Oak Rd"))
            add(Stop("", null))
            add(Stop(null, ""))
        }

        assertEquals(
            expected = expected,
            actual = StopMapTransformer.invoke(stops)
        )
    }

    @Test
    fun `Filter out empty string value while conversion from list of stop to map `() {
        stops.apply {
            add(Stop("", ""))
            add(Stop("5", ""))
            add(Stop("", "Route 6"))
        }

        assertEquals(
            expected = expected,
            actual = StopMapTransformer.invoke(stops)
        )
    }

    @Test
    fun `Verify transformation for empty list`() {
        assertEquals(
            expected = emptyMap(),
            actual = StopMapTransformer.invoke(listOf())
        )
    }

    @Test
    fun `Verify transformation for null input value`() {
        assertEquals(
            expected = emptyMap(),
            actual = StopMapTransformer.invoke(null)
        )
    }
}