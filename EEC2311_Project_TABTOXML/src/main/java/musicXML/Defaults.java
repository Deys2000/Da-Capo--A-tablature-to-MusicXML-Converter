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
 *         &lt;element ref="{}scaling"/&gt;
 *         &lt;element ref="{}page-layout"/&gt;
 *         &lt;element ref="{}system-layout"/&gt;
 *         &lt;element ref="{}staff-layout"/&gt;
 *         &lt;element ref="{}appearance"/&gt;
 *         &lt;element ref="{}music-font"/&gt;
 *         &lt;element ref="{}word-font"/&gt;
 *         &lt;element ref="{}lyric-font"/&gt;
 *         &lt;element ref="{}lyric-language"/&gt;
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
    "scaling",
    "pageLayout",
    "systemLayout",
    "staffLayout",
    "appearance",
    "musicFont",
    "wordFont",
    "lyricFont",
    "lyricLanguage"
})
@XmlRootElement(name = "defaults")
public class Defaults {

    @XmlElement(required = true)
    protected Scaling scaling;
    @XmlElement(name = "page-layout", required = true)
    protected PageLayout pageLayout;
    @XmlElement(name = "system-layout", required = true)
    protected SystemLayout systemLayout;
    @XmlElement(name = "staff-layout", required = true)
    protected StaffLayout staffLayout;
    @XmlElement(required = true)
    protected Appearance appearance;
    @XmlElement(name = "music-font", required = true)
    protected MusicFont musicFont;
    @XmlElement(name = "word-font", required = true)
    protected WordFont wordFont;
    @XmlElement(name = "lyric-font", required = true)
    protected LyricFont lyricFont;
    @XmlElement(name = "lyric-language", required = true)
    protected LyricLanguage lyricLanguage;

    /**
     * Gets the value of the scaling property.
     * 
     * @return
     *     possible object is
     *     {@link Scaling }
     *     
     */
    public Scaling getScaling() {
        return scaling;
    }

    /**
     * Sets the value of the scaling property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scaling }
     *     
     */
    public void setScaling(Scaling value) {
        this.scaling = value;
    }

    /**
     * Gets the value of the pageLayout property.
     * 
     * @return
     *     possible object is
     *     {@link PageLayout }
     *     
     */
    public PageLayout getPageLayout() {
        return pageLayout;
    }

    /**
     * Sets the value of the pageLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageLayout }
     *     
     */
    public void setPageLayout(PageLayout value) {
        this.pageLayout = value;
    }

    /**
     * Gets the value of the systemLayout property.
     * 
     * @return
     *     possible object is
     *     {@link SystemLayout }
     *     
     */
    public SystemLayout getSystemLayout() {
        return systemLayout;
    }

    /**
     * Sets the value of the systemLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemLayout }
     *     
     */
    public void setSystemLayout(SystemLayout value) {
        this.systemLayout = value;
    }

    /**
     * Gets the value of the staffLayout property.
     * 
     * @return
     *     possible object is
     *     {@link StaffLayout }
     *     
     */
    public StaffLayout getStaffLayout() {
        return staffLayout;
    }

    /**
     * Sets the value of the staffLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffLayout }
     *     
     */
    public void setStaffLayout(StaffLayout value) {
        this.staffLayout = value;
    }

    /**
     * Gets the value of the appearance property.
     * 
     * @return
     *     possible object is
     *     {@link Appearance }
     *     
     */
    public Appearance getAppearance() {
        return appearance;
    }

    /**
     * Sets the value of the appearance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Appearance }
     *     
     */
    public void setAppearance(Appearance value) {
        this.appearance = value;
    }

    /**
     * Gets the value of the musicFont property.
     * 
     * @return
     *     possible object is
     *     {@link MusicFont }
     *     
     */
    public MusicFont getMusicFont() {
        return musicFont;
    }

    /**
     * Sets the value of the musicFont property.
     * 
     * @param value
     *     allowed object is
     *     {@link MusicFont }
     *     
     */
    public void setMusicFont(MusicFont value) {
        this.musicFont = value;
    }

    /**
     * Gets the value of the wordFont property.
     * 
     * @return
     *     possible object is
     *     {@link WordFont }
     *     
     */
    public WordFont getWordFont() {
        return wordFont;
    }

    /**
     * Sets the value of the wordFont property.
     * 
     * @param value
     *     allowed object is
     *     {@link WordFont }
     *     
     */
    public void setWordFont(WordFont value) {
        this.wordFont = value;
    }

    /**
     * Gets the value of the lyricFont property.
     * 
     * @return
     *     possible object is
     *     {@link LyricFont }
     *     
     */
    public LyricFont getLyricFont() {
        return lyricFont;
    }

    /**
     * Sets the value of the lyricFont property.
     * 
     * @param value
     *     allowed object is
     *     {@link LyricFont }
     *     
     */
    public void setLyricFont(LyricFont value) {
        this.lyricFont = value;
    }

    /**
     * Gets the value of the lyricLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link LyricLanguage }
     *     
     */
    public LyricLanguage getLyricLanguage() {
        return lyricLanguage;
    }

    /**
     * Sets the value of the lyricLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link LyricLanguage }
     *     
     */
    public void setLyricLanguage(LyricLanguage value) {
        this.lyricLanguage = value;
    }

}