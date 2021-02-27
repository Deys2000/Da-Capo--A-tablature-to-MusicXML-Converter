//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.25 at 11:44:40 AM EST 
//


package musicXML;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}actual-notes"/&gt;
 *         &lt;element ref="{}normal-notes"/&gt;
 *         &lt;element ref="{}normal-type"/&gt;
 *         &lt;element ref="{}normal-dot"/&gt;
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
    "actualNotes",
    "normalNotes",
    "normalType",
    "normalDot"
})
@XmlRootElement(name = "time-modification")
public class TimeModification {

    @XmlElement(name = "actual-notes", required = true)
    protected BigInteger actualNotes;
    @XmlElement(name = "normal-notes", required = true)
    protected BigInteger normalNotes;
    @XmlElement(name = "normal-type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected java.lang.String normalType;
    @XmlElement(name = "normal-dot", required = true)
    protected NormalDot normalDot;

    /**
     * Gets the value of the actualNotes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getActualNotes() {
        return actualNotes;
    }

    /**
     * Sets the value of the actualNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setActualNotes(BigInteger value) {
        this.actualNotes = value;
    }

    /**
     * Gets the value of the normalNotes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNormalNotes() {
        return normalNotes;
    }

    /**
     * Sets the value of the normalNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNormalNotes(BigInteger value) {
        this.normalNotes = value;
    }

    /**
     * Gets the value of the normalType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getNormalType() {
        return normalType;
    }

    /**
     * Sets the value of the normalType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setNormalType(java.lang.String value) {
        this.normalType = value;
    }

    /**
     * Gets the value of the normalDot property.
     * 
     * @return
     *     possible object is
     *     {@link NormalDot }
     *     
     */
    public NormalDot getNormalDot() {
        return normalDot;
    }

    /**
     * Sets the value of the normalDot property.
     * 
     * @param value
     *     allowed object is
     *     {@link NormalDot }
     *     
     */
    public void setNormalDot(NormalDot value) {
        this.normalDot = value;
    }

}