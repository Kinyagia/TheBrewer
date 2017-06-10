package com.example.morris.thebrewer;

/**
 * Created by morris on 6/9/17.
 */

public class Brewery {
    private String mName;
    private String mIsOrganic;


public Brewery(String name, String isOrganic) {
    this.mName = name;
    this.mIsOrganic = isOrganic;

}

    public String getName() {
        return mName;
    }

    public String getIsOrganic() {
        return mIsOrganic;
    }
}
