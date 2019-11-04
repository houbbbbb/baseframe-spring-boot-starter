package com.github.houbbbbb.baseframespringbootstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName GenerateProperties
 * Description TODO
 * Author hbw
 * Date 2019/10/29 10:04
 * Version 1.0
 **/
@ConfigurationProperties(prefix = "generate")
public class GenerateProperties {
    private String enPath;
    private String enPack;
    private String enMPath;
    private int xmlcol1 = 8;
    private int xmlcol2 = 6;

    public String getEnPath() {
        return enPath;
    }

    public void setEnPath(String enPath) {
        this.enPath = enPath;
    }

    public String getEnPack() {
        return enPack;
    }

    public void setEnPack(String enPack) {
        this.enPack = enPack;
    }

    public String getEnMPath() {
        return enMPath;
    }

    public void setEnMPath(String enMPath) {
        this.enMPath = enMPath;
    }

    public int getXmlcol1() {
        return xmlcol1;
    }

    public void setXmlcol1(int xmlcol1) {
        this.xmlcol1 = xmlcol1;
    }

    public int getXmlcol2() {
        return xmlcol2;
    }

    public void setXmlcol2(int xmlcol2) {
        this.xmlcol2 = xmlcol2;
    }
}
