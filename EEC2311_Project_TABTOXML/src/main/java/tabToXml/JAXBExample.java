package tabToXml;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import musicXML.*;

public class JAXBExample {
    
    public static void main(java.lang.String[] args) {
        // Customer customer = new Customer();
        // customer.setId(100);
        // customer.setName("mkyong");
        // customer.setAge(29);
        ScorePartwise s = new ScorePartwise();
        s.setMovementTitle("test");
        Part p = new Part();
        p.setId("1");
        PartList partlist = new PartList();
        ScorePart scorePart = new ScorePart();
        scorePart.setId("1");
        partlist.getPartGroupOrScorePart().add(scorePart);
        s.setPartList(partlist);

        Measure m = new Measure();
        m.setId("1");
        Attributes a = new Attributes();
        a.setDivisions(new BigDecimal(2));
        Key k = new Key();
        k.setFifths(new BigInteger("0"));
        a.setKey(k);
        Time t = new Time();
        t.setBeats("4");
        t.setBeatType("4");
        a.setTime(t);
        Clef c = new Clef();
        c.setSign("TAB");
        c.setLine(new BigInteger("5"));
        a.setClef(c);

        StaffDetails sD = new StaffDetails();
        sD.setStaffLines(new BigInteger("6"));
        StaffTuning sT1 = new StaffTuning();
        sT1.setLine(new BigInteger("1"));
        sT1.setTuningStep("E");
        sT1.setTuningOctave(new BigInteger("2"));

        StaffTuning sT2 = new StaffTuning();
        sT2.setLine(new BigInteger("2"));
        sT2.setTuningStep("A");
        sT2.setTuningOctave(new BigInteger("2"));

        StaffTuning sT3 = new StaffTuning();
        sT3.setLine(new BigInteger("3"));
        sT3.setTuningStep("D");
        sT3.setTuningOctave(new BigInteger("3"));

        StaffTuning sT4 = new StaffTuning();
        sT4.setLine(new BigInteger("4"));
        sT4.setTuningStep("G");
        sT4.setTuningOctave(new BigInteger("3"));

        StaffTuning sT5 = new StaffTuning();
        sT5.setLine(new BigInteger("5"));
        sT5.setTuningStep("B");
        sT5.setTuningOctave(new BigInteger("3"));

        StaffTuning sT6 = new StaffTuning();
        sT6.setLine(new BigInteger("6"));
        sT6.setTuningStep("E");
        sT6.setTuningOctave(new BigInteger("4"));
        ArrayList<StaffTuning> list = new ArrayList<>();
        list.add(sT1);
        list.add(sT2);
        list.add(sT3);
        list.add(sT4);
        list.add(sT5);
        list.add(sT6);

        sD.setStaffTuning(list);

        a.setStaffDetails(sD);
        
        Note n1 = new Note();
        Pitch pitch = new Pitch();
        pitch.setStep("B");
        pitch.setOctave(new BigInteger("2"));
        n1.getDurationOrChordOrCue().add(pitch);
        n1.getDurationOrChordOrCue().add(new BigDecimal("1"));
        n1.setVoice("1");
        Type ty = new Type();
        ty.setValue("eighth");
        n1.setType(ty);


        Notations nts = new Notations();
        Technical tech = new Technical();
        musicXML.String str = new musicXML.String();
        str.setValue(new BigInteger("6"));
        tech.setString(str);
        Fret fret = new Fret();
        fret.setValue(new BigInteger("0"));
        tech.setFret(fret);
        nts.setTechnical(tech);

        n1.setNotations(nts);



        Note n2 = new Note();
        Pitch pitch2 = new Pitch();
        pitch2.setStep("B");
        pitch2.setOctave(new BigInteger("2"));
        n2.getDurationOrChordOrCue().add(pitch2);
        n2.getDurationOrChordOrCue().add(new BigDecimal("1"));
        n2.setVoice("1");
        Type ty2 = new Type();
        ty2.setValue("eighth");
        n2.setType(ty2);


        Notations nts2 = new Notations();
        Technical tech2 = new Technical();
        musicXML.String str2 = new musicXML.String();
        str2.setValue(new BigInteger("5"));
        tech2.setString(str2);
        Fret fret2 = new Fret();
        fret2.setValue(new BigInteger("2"));
        tech2.setFret(fret);
        nts2.setTechnical(tech2);

        n2.setNotations(nts2);



        
        ArrayList<Note> notes = new ArrayList<>();

        notes.add(n1);
        notes.add(n2);

        m.setNote(notes);


        m.setAttributes(a);
        p.setMeasure(m);
        s.setPart(p);

        try {

            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ScorePartwise.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(s, file);
            jaxbMarshaller.marshal(s, System.out);

            } catch (JAXBException e) {
            e.printStackTrace();
            }
    }
}