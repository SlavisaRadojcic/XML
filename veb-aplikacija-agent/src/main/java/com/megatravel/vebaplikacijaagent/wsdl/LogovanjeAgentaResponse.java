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
 *         &lt;element name="uspesno" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "uspesno"
})
@XmlRootElement(name = "logovanjeAgentaResponse", namespace = "http://www.megatravel.com/mikroserviskorisnici/xml")
public class LogovanjeAgentaResponse {

    @XmlElement(namespace = "http://www.megatravel.com/mikroserviskorisnici/xml")
    protected boolean uspesno;

    /**
     * Gets the value of the uspesno property.
     * 
     */
    public boolean isUspesno() {
        return uspesno;
    }

    /**
     * Sets the value of the uspesno property.
     * 
     */
    public void setUspesno(boolean value) {
        this.uspesno = value;
    }

}