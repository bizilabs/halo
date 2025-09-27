package org.bizilabs.halo.base

sealed class TextAreaHeightMode {
    /**
     * Fixed height in number of text lines.
     * The text box will always measure exactly this tall.
     */
    data class Fixed(val lines: Int = 5) : TextAreaHeightMode()

    /**
     * Expandable height:
     * - starts at [minLines]
     * - grows up to [maxLines] before scrolling
     */
    data class Expandable(
        val minLines: Int = 5,
        val maxLines: Int = Int.MAX_VALUE,
    ) : TextAreaHeightMode()
}
