//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.28 at 10:29:37 PM CEST 
//


package com.megatravel.mikroservissmestajnejedinice.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SmestajDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmestajDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kapacitet" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="dozvoljenoOtkazivanje" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="brojDanaZaOtkazivanje" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ocena" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="agent" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="adresaDTO" type="{http://www.megatravel.com/mikroservissmestajnejedinice/xml}AdresaDTO"/>
 *         &lt;element name="tipDTO" type="{http://www.megatravel.com/mikroservissmestajnejedinice/xml}TipSmestajaDTO"/>
 *         &lt;element name="uslugeDTO" type="{http://www.megatravel.com/mikroservissmestajnejedinice/xml}UslugaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmestajDTO", propOrder = {
    "id",
    "opis",
    "kapacitet",
    "cena",
    "dozvoljenoOtkazivanje",
    "brojDanaZaOtkazivanje",
    "ocena",
    "agent",
    "adresaDTO",
    "tipDTO",
    "uslugeDTO"
})
public class SmestajDTO {

    protected long id;
    @XmlElement(required = true)
    protected String opis;
    protected int kapacitet;
    protected double cena;
    protected boolean dozvoljenoOtkazivanje;
    protected int brojDanaZaOtkazivanje;
    protected double ocena;
    protected long agent;
    @XmlElement(required = true)
    protected AdresaDTO adresaDTO;
    @XmlElement(required = true)
    protected TipSmestajaDTO tipDTO;
    protected List<UslugaDTO> uslugeDTO;

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
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the kapacitet property.
     * 
     */
    public int getKapacitet() {
        return kapacitet;
    }

    /**
     * Sets the value of the kapacitet property.
     * 
     */
    public void setKapacitet(int value) {
        this.kapacitet = value;
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
     * Gets the value of the dozvoljenoOtkazivanje property.
     * 
     */
    public boolean isDozvoljenoOtkazivanje() {
        return dozvoljenoOtkazivanje;
    }

    /**
     * Sets the value of the dozvoljenoOtkazivanje property.
     * 
     */
    public void setDozvoljenoOtkazivanje(boolean value) {
        this.dozvoljenoOtkazivanje = value;
    }

    /**
     * Gets the value of the brojDanaZaOtkazivanje property.
     * 
     */
    public int getBrojDanaZaOtkazivanje() {
        return brojDanaZaOtkazivanje;
    }

    /**
     * Sets the value of the brojDanaZaOtkazivanje property.
     * 
     */
    public void setBrojDanaZaOtkazivanje(int value) {
        this.brojDanaZaOtkazivanje = value;
    }

    /**
     * Gets the value of the ocena property.
     * 
     */
    public double getOcena() {
        return ocena;
    }

    /**
     * Sets the value of the ocena property.
     * 
     */
    public void setOcena(double value) {
        this.ocena = value;
    }

    /**
     * Gets the value of the agent property.
     * 
     */
    public long getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     * 
     */
    public void setAgent(long value) {
        this.agent = value;
    }

    /**
     * Gets the value of the adresaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link AdresaDTO }
     *     
     */
    public AdresaDTO getAdresaDTO() {
        return adresaDTO;
    }

    /**
     * Sets the value of the adresaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdresaDTO }
     *     
     */
    public void setAdresaDTO(AdresaDTO value) {
        this.adresaDTO = value;
    }

    /**
     * Gets the value of the tipDTO property.
     * 
     * @return
     *     possible object is
     *     {@link TipSmestajaDTO }
     *     
     */
    public TipSmestajaDTO getTipDTO() {
        return tipDTO;
    }

    /**
     * Sets the value of the tipDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipSmestajaDTO }
     *     
     */
    public void setTipDTO(TipSmestajaDTO value) {
        this.tipDTO = value;
    }

    /**
     * Gets the value of the uslugeDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uslugeDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUslugeDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UslugaDTO }
     * 
     * 
     */
    public List<UslugaDTO> getUslugeDTO() {
        if (uslugeDTO == null) {
            uslugeDTO = new ArrayList<UslugaDTO>();
        }
        return this.uslugeDTO;
    }

}