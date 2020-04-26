package com.goldknight;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Zone {

      public String zid;
      public String zname;

       public Zone() {

       }

    public Zone(String zid, String zname) {
        this.zid = zid;
        this.zname = zname;
    }


    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }
}



