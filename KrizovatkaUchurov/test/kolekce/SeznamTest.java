/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author st55440
 */
public class SeznamTest {

    public SeznamTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPocet() throws KolekceException {
        System.out.println("getPocet");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(1);
        instance.pridej(1);
        int expResult = 3;
        int result = instance.getPocet();
        assertEquals(expResult, result);
    }

    @Test(expected = KolekceException.class)
    public void testNastavPrvni() throws Exception {
        System.out.println("nastavPrvni");
        Seznam instance = new Seznam();
        instance.nastavPrvni();
    }

    @Test
    public void testJeDalsi() throws Exception {
        System.out.println("jeDalsi");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(3);
        instance.nastavPrvni();
        boolean expResult = true;
        boolean result = instance.jeDalsi();
        assertEquals(expResult, result);
    }

    @Test(expected = KolekceException.class)
    public void testPrejdiNaDalsi() throws Exception {
        System.out.println("prejdiNaDalsi");
        Seznam instance = new Seznam();
        instance.nastavPrvni();
        instance.prejdiNaDalsi();
    }

    @Test
    public void testJePrazdny() {
        System.out.println("jePrazdny");
        Seznam instance = new Seznam();
        assertTrue(instance.jePrazdny());
    }

    @Test
    public void testPridej() throws Exception {
        System.out.println("pridej");
        Object data = null;
        Seznam instance = new Seznam();
        instance.pridej(data);
        Object ex = 1;
        Object result = instance.getPocet();
        assertEquals(result, ex);
    }

    @Test
    public void testOdeberPrvni() throws Exception {
        System.out.println("odeberPrvni");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Object expResult = 1;
        Object result = instance.odeberPrvni();
        assertEquals(expResult, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void test001Iterator() {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        Iterator iterator = instance.iterator();
        iterator.next();
    }

    public void test002Iterator() throws KolekceException {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        instance.pridej(2);
        Iterator iterator = instance.iterator();
        assertFalse(iterator.hasNext());
    }

    public void test003Iterator() throws KolekceException {
        System.out.println("iterator");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Iterator iterator = instance.iterator();
        Object data = iterator.next();
        Object data2 = iterator.next();
        Object data3 = iterator.next();
        assertEquals(data, 1);
        assertEquals(data2, 2);
        assertEquals(data2, 3);
    }

    @Test
    public void test001Zpristupni() throws Exception {
        System.out.println("zpristupni");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.nastavPrvni();
        Object expResult = 1;
        Object result = instance.zpristupni();
        assertEquals(expResult, result);
    }

    @Test(expected = KolekceException.class)
    public void test002Zpristupni() throws Exception {
        System.out.println("zpristupni");
        Seznam instance = new Seznam();
        instance.nastavPrvni();
        Object expResult = null;
        Object result = instance.zpristupni();
    }

    @Test
    public void testToArray_0args() throws KolekceException {
        System.out.println("toArray");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(3);
        Object[] expResult = {1, 2, 3};
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testToArray_GenericType() throws KolekceException {
        System.out.println("toArray");
        Object[] array = {1, 2, 3, 4};
        Seznam instance = new Seznam();
        instance.pridej(array);
        instance.toArray();
        Object[] result = instance.toArray(array);
        assertArrayEquals(array, result);
    }

    @Test
    public void test001OdeberPosledni() throws Exception {
        System.out.println("odeberPosledni");
        Seznam instance = new Seznam();
        instance.pridej(1);
        instance.pridej(2);
        instance.pridej(199);
        Object expResult = 199;
        Object result = instance.odeberPosledni();
        assertEquals(expResult, result);
    }

    @Test(expected = KolekceException.class)
    public void test002OdeberPosledni() throws Exception {
        System.out.println("odeberPosledni");
        Seznam instance = new Seznam();
        instance.odeberPosledni();

    }

    @Test
    public void testZrus() throws KolekceException {
        System.out.println("zrus");
        Seznam instance = new Seznam();
         instance.pridej(1);
        instance.pridej(2);
        instance.zrus();
        assertTrue(instance.jePrazdny());
    }
 
}
