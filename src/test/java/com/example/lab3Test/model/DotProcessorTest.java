package com.example.lab3Test.model;

import com.example.lab3.entities.Dot;
import com.example.lab3.model.DotProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DotProcessorTest {

    @Test
    void testDotProcessorSegment1Success() {
        Dot dot = new Dot(1.0, 1.0, 3.0);
        Assertions.assertTrue(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment1Fail() {
        Dot dot = new Dot(2.0, 2.0, 3.99);
        Assertions.assertFalse(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment2Fail() {
        Dot dot = new Dot(-0.001, 0.001, 1000);
        Assertions.assertFalse(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment3Success() {
        Dot dot = new Dot(-0.1, -1, 3);
        Assertions.assertTrue(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment3Fail() {
        Dot dot = new Dot(-1, -2, 3);
        Assertions.assertFalse(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment4Success() {
        Dot dot = new Dot(3, -0.99, 8);
        Assertions.assertTrue(DotProcessor.processDot(dot));
    }

    @Test
    void testDotProcessorSegment4Fail() {
        Dot dot = new Dot(3, -1.01, 8);
        Assertions.assertFalse(DotProcessor.processDot(dot));
    }
    
}
