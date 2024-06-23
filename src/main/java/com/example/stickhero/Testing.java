package com.example.stickhero;


import javafx.scene.layout.AnchorPane;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Testing {
    @Test
    public void testStick()
    {
        AnchorPane a=new AnchorPane();
        Stick s=new Stick(a,208,50);
        assertEquals(s.getStickRotating(),0);
    }
    @Test
    public void testAnimation()
    {
        AnchorPane a=new AnchorPane();
        Stick s=new Stick(a,208,50);
        s.setStickRotating(0);
        s.setElongating(true);
        assertEquals(s.getStickRotating(),0);
    }
    @Test
    public void testInitialStickState() {
        AnchorPane anchorPane = new AnchorPane();
        Stick stick = new Stick(anchorPane, 208, 50);

        // Verify the initial state of the stick
        assertEquals(0, stick.getStickRotating());
        assertFalse(stick.isElongating());
    }
    @Before
    public void prin()
    {
        System.out.println("Test Running");
    }
}
