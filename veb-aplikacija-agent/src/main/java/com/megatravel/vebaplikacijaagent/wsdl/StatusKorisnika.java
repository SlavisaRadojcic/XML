//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.28 at 10:29:58 PM CEST 
//


package com.megatravel.vebaplikacijaagent.wsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusKorisnika.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusKorisnika"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AKTIVAN"/&gt;
 *     &lt;enumeration value="BLOKIRAN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatusKorisnika", namespace = "http://www.megatravel.com/mikroserviskorisnici/xml")
@XmlEnum
public enum StatusKorisnika {

    AKTIVAN,
    BLOKIRAN;

    public String value() {
        return name();
    }

    public static StatusKorisnika fromValue(String v) {
        return valueOf(v);
    }

}
