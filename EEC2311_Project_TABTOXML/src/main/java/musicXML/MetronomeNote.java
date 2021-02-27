//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.25 at 11:44:40 AM EST 
//


package musicXML;

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
 *         &lt;element ref="{}metronome-type"/&gt;
 *         &lt;element ref="{}metronome-dot"/&gt;
 *         &lt;element ref="{}metronome-beam"/&gt;
 *         &lt;element ref="{}metronome-tied"/&gt;
 *         &lt;element ref="{}metronome-tuplet"/&gt;
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
    "metronomeType",
    "metronomeDot",
    "metronomeBeam",
    "metronomeTied",
    "metronomeTuplet"
})
@XmlRootElement(name = "metronome-note")
public class MetronomeNote {

    @XmlElement(name = "metronome-type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected java.lang.String metronomeType;
    @XmlElement(name = "metronome-dot", required = true)
    protected MetronomeDot metronomeDot;
    @XmlElement(name = "metronome-beam", required = true)
    protected MetronomeBeam metronomeBeam;
    @XmlElement(name = "metronome-tied", required = true)
    protected MetronomeTied metronomeTied;
    @XmlElement(name = "metronome-tuplet", required = true)
    protected MetronomeTuplet metronomeTuplet;

    /**
     * Gets the value of the metronomeType property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getMetronomeType() {
        return metronomeType;
    }

    /**
     * Sets the value of the metronomeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setMetronomeType(java.lang.String value) {
        this.metronomeType = value;
    }

    /**
     * Gets the value of the metronomeDot property.
     * 
     * @return
     *     possible object is
     *     {@link MetronomeDot }
     *     
     */
    public MetronomeDot getMetronomeDot() {
        return metronomeDot;
    }

    /**
     * Sets the value of the metronomeDot property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetronomeDot }
     *     
     */
    public void setMetronomeDot(MetronomeDot value) {
        this.metronomeDot = value;
    }

    /**
     * Gets the value of the metronomeBeam property.
     * 
     * @return
     *     possible object is
     *     {@link MetronomeBeam }
     *     
     */
    public MetronomeBeam getMetronomeBeam() {
        return metronomeBeam;
    }

    /**
     * Sets the value of the metronomeBeam property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetronomeBeam }
     *     
     */
    public void setMetronomeBeam(MetronomeBeam value) {
        this.metronomeBeam = value;
    }

    /**
     * Gets the value of the metronomeTied property.
     * 
     * @return
     *     possible object is
     *     {@link MetronomeTied }
     *     
     */
    public MetronomeTied getMetronomeTied() {
        return metronomeTied;
    }

    /**
     * Sets the value of the metronomeTied property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetronomeTied }
     *     
     */
    public void setMetronomeTied(MetronomeTied value) {
        this.metronomeTied = value;
    }

    /**
     * Gets the value of the metronomeTuplet property.
     * 
     * @return
     *     possible object is
     *     {@link MetronomeTuplet }
     *     
     */
    public MetronomeTuplet getMetronomeTuplet() {
        return metronomeTuplet;
    }

    /**
     * Sets the value of the metronomeTuplet property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetronomeTuplet }
     *     
     */
    public void setMetronomeTuplet(MetronomeTuplet value) {
        this.metronomeTuplet = value;
    }

}
