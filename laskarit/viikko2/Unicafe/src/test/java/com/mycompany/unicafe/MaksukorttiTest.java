package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(700);
        kassa = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(kortti.saldo(), 700);
    }
    
    @Test
    public void latausToimii() {
        kortti.lataaRahaa(300);
        assertEquals(kortti.saldo(), 1000);
    }
    
    @Test
    public void otaRahaaFail() {
        assertFalse(kortti.otaRahaa(1000));
    }
    
    @Test
    public void rahaaTarpeeksiEdullisesti() {
        kassa.syoEdullisesti(kortti);
        assertEquals(kortti.saldo(), 460);
    }
    
    @Test
    public void rahaaTarpeeksiMaukkaasti() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 300);
    }
    
    @Test
    public void rahaaEiTarpeeksiEdullisesti() {
        kortti = new Maksukortti(2);
        kassa.syoEdullisesti(kortti);
        assertEquals(kortti.saldo(), 2);
    }
    
    @Test
    public void rahaaEiTarpeeksiMaukkaasti() {
        kortti = new Maksukortti(2);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 2);
    }
    
    @Test
    public void rahaaTarpeeksiEdullisestiBoolean() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahaaTarpeeksiMaukkaastiBoolean() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahaaEiTarpeeksiEdullisestiBoolean() {
        kortti = new Maksukortti(2);
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahaaEiTarpeeksiMaukkaastiBoolean() {
        kortti = new Maksukortti(2);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void tulostus() {
        assertEquals("saldo: 7.0", kortti.toString());
    }
}
