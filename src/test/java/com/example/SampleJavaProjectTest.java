package com.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class SampleJavaProjectTest {

    private SampleJavaProject sjp;

    @Before
    public final void setUp() {
        sjp = new SampleJavaProject();
    }

    @Test
    public final void testGetSet() {
        sjp.setName("foo");
        assertEquals("foo", sjp.getName());
    }

    @Test(expected = NullPointerException.class)
    public final void nullTest() {
        sjp.setName(null);
    }

    @Test
    public final void testSayHello() {
        // Redirect system output to capture the printed message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Test default name
        sjp.setName("world");
        sjp.sayHello();
        assertEquals("Hello, world!\n", outContent.toString());

        // Test custom name
        outContent.reset(); // Reset output stream
        sjp.setName("Alice");
        sjp.sayHello();
        assertEquals("Hello, Alice!\n", outContent.toString());

        // Reset system output
        System.setOut(originalOut);
    }

    @Test
    public final void testMainVerbose() {
        //empty method
    }
}
