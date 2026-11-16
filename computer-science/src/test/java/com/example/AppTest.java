package com.example;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws NoSuchFieldException, IllegalAccessException {

        App.main(new String[0]);
    }

    @Test
    public void shiftRight(){
        int i = 10;
        i = i + (i>>1);
        System.out.println(i);
        i = i + (i>>1);
        System.out.println(i);
    }
}
