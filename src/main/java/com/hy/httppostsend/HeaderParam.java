package com.hy.httppostsend;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

/**
 * Created by haoy on 2017/7/12.
 */
public class HeaderParam implements Header{

    public String getName() {
        return null;
    }

    public String getValue() {
        return null;
    }

    public HeaderElement[] getElements() throws ParseException {
        return new HeaderElement[0];
    }
}
