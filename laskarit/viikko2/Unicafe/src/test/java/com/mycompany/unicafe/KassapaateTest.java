
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(450);
    }
    
    @Test
    public void kassassaRahaaAlussa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lounaitaMyytyAlussa() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullinen() {
        int vaihtoraha = kassa.syoEdullisesti(250);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(10, vaihtoraha);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        
        int vaihtoraha2 = kassa.syoEdullisesti(vaihtoraha);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(10, vaihtoraha2);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukas() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(100, vaihtoraha);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        
        int vaihtoraha2 = kassa.syoMaukkaasti(vaihtoraha);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(100, vaihtoraha2);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEdullinen() {
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertTrue(onnistui);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        
        boolean onnistui2 = kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertFalse(onnistui2);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoMaukas() {
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertTrue(onnistui);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        
        boolean onnistui2 = kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertFalse(onnistui2);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLataus() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
        assertEquals(950, kortti.saldo());
        
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(100500, kassa.kassassaRahaa());
        assertEquals(950, kortti.saldo());
    }
}
