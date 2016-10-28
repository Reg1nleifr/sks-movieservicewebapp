package xmlAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Flo on 28/10/2016.
 */
public class SqlDateAdapter extends XmlAdapter<String, Date> {
    DateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Date unmarshal(String v) throws Exception {
        return Date.valueOf(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return format.format(v);
    }
}