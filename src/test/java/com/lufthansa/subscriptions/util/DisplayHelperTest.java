package com.lufthansa.subscriptions.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DisplayHelper Test")
class DisplayHelperTest {

    @Test
    @DisplayName("Should format number with two decimal places - normal case")
    void testFormatNumbersWithTwoDecimal_NormalCase() {
        // Arrange
        Double input = 123.456;
        Double expected = 123.46;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should round to two decimal places");
    }

    @Test
    @DisplayName("Should format number with two decimal places - single decimal")
    void testFormatNumbersWithTwoDecimal_SingleDecimal() {
        // Arrange
        Double input = 99.9;
        Double expected = 99.9;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should keep single decimal place");
    }

    @Test
    @DisplayName("Should format number with two decimal places - no decimal")
    void testFormatNumbersWithTwoDecimal_NoDecimal() {
        // Arrange
        Double input = 100.0;
        Double expected = 100.0;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle whole numbers");
    }

    @Test
    @DisplayName("Should format number with two decimal places - rounding down")
    void testFormatNumbersWithTwoDecimal_RoundingDown() {
        // Arrange
        Double input = 10.123;
        Double expected = 10.12;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should round down when third decimal < 5");
    }

    @Test
    @DisplayName("Should format number with two decimal places - rounding up")
    void testFormatNumbersWithTwoDecimal_RoundingUp() {
        // Arrange
        Double input = 10.128;
        Double expected = 10.13;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should round up when third decimal >= 5");
    }

    @Test
    @DisplayName("Should format zero correctly")
    void testFormatNumbersWithTwoDecimal_Zero() {
        // Arrange
        Double input = 0.0;
        Double expected = 0.0;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle zero correctly");
    }

    @Test
    @DisplayName("Should format negative number with two decimal places")
    void testFormatNumbersWithTwoDecimal_NegativeNumber() {
        // Arrange
        Double input = -123.456;
        Double expected = -123.46;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle negative numbers correctly");
    }

    @Test
    @DisplayName("Should format very small number with two decimal places")
    void testFormatNumbersWithTwoDecimal_VerySmallNumber() {
        // Arrange
        Double input = 0.001;
        Double expected = 0.0;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should format very small numbers to zero");
    }

    @Test
    @DisplayName("Should format large number with two decimal places")
    void testFormatNumbersWithTwoDecimal_LargeNumber() {
        // Arrange
        Double input = 999999.999;
        Double expected = 1000000.0;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle large numbers correctly");
    }

    @Test
    @DisplayName("Should handle number with exact two decimal places")
    void testFormatNumbersWithTwoDecimal_ExactTwoDecimals() {
        // Arrange
        Double input = 50.55;
        Double expected = 50.55;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should preserve exact two decimal places");
    }

    @Test
    @DisplayName("Should handle very large number")
    void testFormatNumbersWithTwoDecimal_VeryLargeNumber() {
        // Arrange
        Double input = 1234567890.123;
        Double expected = 1234567890.12;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle very large numbers");
    }

    @Test
    @DisplayName("Should handle negative number rounding")
    void testFormatNumbersWithTwoDecimal_NegativeRounding() {
        // Arrange
        Double input = -10.567;
        Double expected = -10.57;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should handle negative number rounding correctly");
    }

    @Test
    @DisplayName("Should handle decimal that rounds to .99")
    void testFormatNumbersWithTwoDecimal_RoundsToNinetyNine() {
        // Arrange
        Double input = 10.994;
        Double expected = 10.99;

        // Act
        Double result = DisplayHelper.formatNumbersWithTwoDecimal(input);

        // Assert
        assertEquals(expected, result, 0.001, "Should correctly round to .99");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when input is null")
    void testFormatNumbersWithTwoDecimal_NullInput() {
        // Arrange
        Double input = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            DisplayHelper.formatNumbersWithTwoDecimal(input);
        }, "Should throw IllegalArgumentException for null input");
    }
}

