package com.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SampleJavaProjectTest {

    private SampleJavaProject sjp;
    private JCommander mockJCommander;

    @Before
    public final void setUp() {
        sjp = new SampleJavaProject();
        mockJCommander = Mockito.mock(JCommander.class);
    }

    @Test
    public final void testGetSet() {
        sjp.setName("Foo");
        assertEquals("Foo", sjp.getName());
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
        // Mock JCommander usage
        String[] args = {"--verbose"};
        when(mockJCommander.usage()).thenReturn(null);

        // Create a spy of SampleJavaProject to verify interactions
        SampleJavaProject spySjp = spy(new SampleJavaProject());
        doNothing().when(spySjp).run();

        // Set up command-line arguments and verify behavior
        try {
            JCommander jc = new JCommander(spySjp, args);
            if (spySjp.isVerbose()) {
                System.out.println("Verbose output enabled.");
            }
            spySjp.run();
        } catch (ParameterException e) {
            System.err.println("error: " + e.getMessage());
            new JCommander(new SampleJavaProject()).usage();
            System.exit(-1);
        }

        // Verify that verbose output is enabled
        assertTrue(spySjp.isVerbose());
        verify(spySjp).run();
    }
}
