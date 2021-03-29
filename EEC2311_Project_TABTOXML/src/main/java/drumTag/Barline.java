//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.25 at 11:44:40 AM EST 
//


package drumTag;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}bar-style"/&gt;
 *         &lt;element ref="{}footnote"/&gt;
 *         &lt;element ref="{}level"/&gt;
 *         &lt;element ref="{}wavy-line"/&gt;
 *         &lt;element ref="{}fermata"/&gt;
 *         &lt;element ref="{}ending"/&gt;
 *         &lt;element ref="{}repeat"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="coda" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="divisions" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="location" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="segno" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "barStyle",
    "footnote",
    "level",
    "wavyLine",
    "fermata",
    "ending",
    "repeat"
})
@XmlRootElement(name = "barline")
public class Barline {

    @XmlElement(name = "bar-style", required = true)
    protected BarStyle barStyle;
    @XmlElement(required = true)
    protected Footnote footnote;
    @XmlElement(required = true)
    protected Level level;
    @XmlElement(name = "wavy-line", required = true)
    protected WavyLine wavyLine;
    @XmlElement(required = true)
    protected Fermata fermata;
    @XmlElement(required = true)
    protected Ending ending;
    @XmlElement(required = true)
    protected Repeat repeat;
    @XmlAttribute(name = "coda", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected java.lang.String coda;
    @XmlAttribute(name = "divisions", required = true)
    protected BigDecimal divisions;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected java.lang.String id;
    @XmlAttribute(name = "location", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected java.lang.String location;
    @XmlAttribute(name = "segno", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected java.lang.String segno;

    /**
     * Gets the value of the barStyle property.
     * 
     * @return
     *     possible object is
     *     {@link BarStyle }
     *     
     */
    public BarStyle getBarStyle() {
        return barStyle;
    }

    /**
     * Sets the value of the barStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BarStyle }
     *     
     */
    public void setBarStyle(BarStyle value) {
        this.barStyle = value;
    }

    /**
     * Gets the value of the footnote property.
     * 
     * @return
     *     possible object is
     *     {@link Footnote }
     *     
     */
    public Footnote getFootnote() {
        return footnote;
    }

    /**
     * Sets the value of the footnote property.
     * 
     * @param value
     *     allowed object is
     *     {@link Footnote }
     *     
     */
    public void setFootnote(Footnote value) {
        this.footnote = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link Level }
     *     
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link Level }
     *     
     */
    public void setLevel(Level value) {
        this.level = value;
    }

    /**
     * Gets the value of the wavyLine property.
     * 
     * @return
     *     possible object is
     *     {@link WavyLine }
     *     
     */
    public WavyLine getWavyLine() {
        return wavyLine;
    }

    /**
     * Sets the value of the wavyLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link WavyLine }
     *     
     */
    public void setWavyLine(WavyLine value) {
        this.wavyLine = value;
    }

    /**
     * Gets the value of the fermata property.
     * 
     * @return
     *     possible object is
     *     {@link Fermata }
     *     
     */
    public Fermata getFermata() {
        return fermata;
    }

    /**
     * Sets the value of the fermata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fermata }
     *     
     */
    public void setFermata(Fermata value) {
        this.fermata = value;
    }

    /**
     * Gets the value of the ending property.
     * 
     * @return
     *     possible object is
     *     {@link Ending }
     *     
     */
    public Ending getEnding() {
        return ending;
    }

    /**
     * Sets the value of the ending property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ending }
     *     
     */
    public void setEnding(Ending value) {
        this.ending = value;
    }

    /**
     * Gets the value of the repeat property.
     * 
     * @return
     *     possible object is
     *     {@link Repeat }
     *     
     */
    public Repeat getRepeat() {
        return repeat;
    }

    /**
     * Sets the value of the repeat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Repeat }
     *     
     */
    public void setRepeat(Repeat value) {
        this.repeat = value;
    }

    /**
     * Gets the value of the coda property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getCoda() {
        return coda;
    }

    /**
     * Sets the value of the coda property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setCoda(java.lang.String value) {
        this.coda = value;
    }

    /**
     * Gets the value of the divisions property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDivisions() {
        return divisions;
    }

    /**
     * Sets the value of the divisions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDivisions(BigDecimal value) {
        this.divisions = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setLocation(java.lang.String value) {
        this.location = value;
    }

    /**
     * Gets the value of the segno property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getSegno() {
        return segno;
    }

    /**
     * Sets the value of the segno property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setSegno(java.lang.String value) {
        this.segno = value;
    }

}