package at.technikumwien.services.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Flo & Stefan
 * Date Adapter for Xml
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String v) throws Exception
    {
        return LocalDate.parse(v, formatter);
    }

    @Override
    public String marshal(LocalDate v) throws Exception
    {
        if (v == null) {
            return "N/A";
        }
        return v.format(formatter);
    }
}
