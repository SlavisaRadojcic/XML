//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.28 at 10:29:37 PM CEST 
//


package com.megatravel.mikroservissmestajnejedinice.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RezervacijaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RezervacijaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="realizovana" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="korisnik" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ocena" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="smestaj" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pocetak" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="kraj" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RezervacijaDTO", propOrder = {
    "id",
    "cena",
    "realizovana",
    "korisnik",
    "ocena",
    "smestaj",
    "pocetak",
    "kraj"
})
public class RezervacijaDTO {

    protected long id;
    protected double cena;
    protected boolean realizovana;
    protected long korisnik;
    protected long ocena;
    protected long smestaj;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar pocetak;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar kraj;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the cena property.
     * 
     */
    public double getCena() {
        return cena;
    }

    /**
     * Sets the value of the cena property.
     * 
     */
    public void setCena(double value) {
        this.cena = value;
    }

    /**
     * Gets the value of the realizovana property.
     * 
     */
    public boolean isRealizovana() {
        return realizovana;
    }

    /**
     * Sets the value of the realizovana property.
     * 
     */
    public void setRealizovana(boolean value) {
        this.realizovana = value;
    }

    /**
     * Gets the value of the korisnik property.
     * 
     */
    public long getKorisnik() {
        return korisnik;
    }

    /**
     * Sets the value of the korisnik property.
     * 
     */
    public void setKorisnik(long value) {
        this.korisnik = value;
    }

    /**
     * Gets the value of the ocena property.
     * 
     */
    public long getOcena() {
        return ocena;
    }

    /**
     * Sets the value of the ocena property.
     * 
     */
    public void setOcena(long value) {
        this.ocena = value;
    }

    /**
     * Gets the value of the smestaj property.
     * 
     */
    public long getSmestaj() {
        return smestaj;
    }

    /**
     * Sets the value of the smestaj property.
     * 
     */
    public void setSmestaj(long value) {
        this.smestaj = value;
    }

    /**
     * Gets the value of the pocetak property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPocetak() {
        return pocetak;
    }

    /**
     * Sets the value of the pocetak property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPocetak(XMLGregorianCalendar value) {
        this.pocetak = value;
    }

    /**
     * Gets the value of the kraj property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getKraj() {
        return kraj;
    }

    /**
     * Sets the value of the kraj property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setKraj(XMLGregorianCalendar value) {
        this.kraj = value;
    }

}