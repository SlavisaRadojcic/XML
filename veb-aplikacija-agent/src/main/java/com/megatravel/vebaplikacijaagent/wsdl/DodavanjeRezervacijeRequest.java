//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.28 at 10:29:58 PM CEST 
//


package com.megatravel.vebaplikacijaagent.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rezervacija" type="{http://www.megatravel.com/mikroservissmestajnejedinice/xml}RezervacijaDTO"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rezervacija"
})
@XmlRootElement(name = "dodavanjeRezervacijeRequest")
public class DodavanjeRezervacijeRequest {

    @XmlElement(required = true)
    protected RezervacijaDTO rezervacija;

    /**
     * Gets the value of the rezervacija property.
     * 
     * @return
     *     possible object is
     *     {@link RezervacijaDTO }
     *     
     */
    public RezervacijaDTO getRezervacija() {
        return rezervacija;
    }

    /**
     * Sets the value of the rezervacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link RezervacijaDTO }
     *     
     */
    public void setRezervacija(RezervacijaDTO value) {
        this.rezervacija = value;
    }

}
