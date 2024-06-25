package com.promineotech;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TestDemoJUnitTest {

    private TestDemo testDemo;

    @BeforeEach
    void setUp() {
        testDemo = new TestDemo();
    }

    @Test
    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
    	  assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
          assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
          assertThat(testDemo.addPositive(1, 1)).isEqualTo(2);
          assertThat(testDemo.addPositive(10, 20)).isEqualTo(30);
    }
    
    @Test
    void assertThatPairsOfNumbersAreMultipliedCorrectly() {
        assertThat(testDemo.multiply(3, 4)).isEqualTo(12);
        assertThat(testDemo.multiply(7, 5)).isEqualTo(35);
        assertThat(testDemo.multiply(6, 6)).isEqualTo(36);
        assertThat(testDemo.multiply(0, 5)).isEqualTo(0);
    }
    
    
    @ParameterizedTest
    @MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
    	 if (!expectException) {
             assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else {
        	assertThatThrownBy(() -> testDemo.addPositive(a, b))
            .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void assertThatNumberSquaredIsCorrect() {
        TestDemo mockDemo = spy(testDemo);
        doReturn(5).when(mockDemo).getRandomInt();
        int fiveSquared = mockDemo.randomNumberSquared();
        assertThat(fiveSquared).isEqualTo(25);
    }
    
    static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
            arguments(1, 2, 3, false),
            arguments(5, 5, 10, false),
            arguments(10, 20, 30, false),
            arguments(100, 200, 300, false),
            arguments(-1, 2, 0, true),
            arguments(1, -2, 0, true),
            arguments(0, 2, 0, true),
            arguments(2, 0, 0, true)
        );
    }
}
