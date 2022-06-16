package transformertest

import model.Direction
import org.junit.jupiter.api.Test
import transformer.DirectionMapTransformer
import kotlin.test.assertEquals

class DirectionMapTransformerTest {
    private val directions = mutableListOf(
        Direction(0, "Eastbound"),
        Direction(1, "Westbound")
    )

    private val expected = mapOf(
        "Eastbound" to 0,
        "Westbound" to 1
    )

    @Test
    fun `Verify transformation from list of direction to map`() {
        assertEquals(
            expected = expected,
            actual = DirectionMapTransformer.invoke(directions)
        )
    }

    @Test
    fun `Filter out null while conversion from list of route to map`() {
        directions.apply { add(Direction(3, null)) }

        assertEquals(
            expected = expected,
            actual = DirectionMapTransformer.invoke(directions)
        )
    }

    @Test
    fun `Filter out empty string value while conversion from list of route to map`() {
        directions.apply {  add(Direction(4, "")) }

        assertEquals(
            expected = expected,
            actual = DirectionMapTransformer.invoke(directions)
        )
    }

    @Test
    fun `Verify transformation for empty list`() {
        assertEquals(
            expected = emptyMap(),
            actual = DirectionMapTransformer.invoke(listOf())
        )
    }

    @Test
    fun `Verify transformation for null input value`() {
        assertEquals(
            expected = emptyMap(),
            actual = DirectionMapTransformer.invoke(null)
        )
    }
}