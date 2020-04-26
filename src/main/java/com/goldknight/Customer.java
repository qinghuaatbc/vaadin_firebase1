package com.goldknight;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Customer {

      public String addr_address;
      public String addr_city;
      public String id;


       public Customer() {

       }


    public String getAddr_address() {
        return addr_address;
    }

    public void setAddr_address(String addr_address) {
        this.addr_address = addr_address;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



