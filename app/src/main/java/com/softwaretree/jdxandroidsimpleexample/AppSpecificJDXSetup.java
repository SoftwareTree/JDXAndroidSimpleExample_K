/**
 * Copyright (c) 2011-2014 Software Tree, LLC.
 */
package com.softwaretree.jdxandroidsimpleexample;

import com.softwaretree.jdx.JDXSetup;
import com.softwaretree.jdxandroid.BaseAppSpecificJDXSetup;

import android.content.ContextWrapper;


/**
 * This class is a shell to supply some basic application specific parameters 
 * for JDXA ORM setup. The superclass does the bulk of the work.  After once calling 
 * {@link #initialize()} method, you may call {@link #getInstance(android.content.ContextWrapper)}
 * method to get and work with a {@link com.softwaretree.jdx.JDXSetup} object. 
 * <p>
 * @author Damodar Periwal
 */
public class AppSpecificJDXSetup extends BaseAppSpecificJDXSetup {
	// Resource id of the object relational mapping (.jdx) file
	private static int ormId = R.raw.simple_example;
    
    /**
     * Initializes the ORMFileResourceId {@link #setORMFileResourceId(int)} of the file 
     * containing the JDX mapping specification.
     * <p>  
     * The license key for the JDX for Android ORM product must also be set in this 
     * method using the corresponding setter method {@link #setJdxForAndroidLicenseKey(int, String)} of the superclass.
     * <p>  
     * Any non-default values for the following parameters should be set in this 
     * method using the corresponding setter methods of the superclass:
     * <ul>
     * <li>forceCreateSchema {@link #setForceCreateSchema(int, boolean)} (default false)</li>
     * <li>debugLevel {@link #setDebugLevel(int, int)} (default 5)</li>
     * </ul>
     * This method should be called before calling the getInstance() method of this class.
     */
    public static void initialize() {
        setORMFileResourceId(ormId);
        setJdxForAndroidLicenseKey(ormId, "hPU01.0cCsupNaU0ulc85z1uf9wR5N95NNzjdxR7d5uTd1Tp3lNNxRp3l276");
        setForceCreateSchema(ormId, true);
        //setDebugLevel(ormId, 3);
    }
    
	public static JDXSetup getInstance(ContextWrapper contextWrapper) throws Exception {
		return getInstance(ormId, contextWrapper);
	}

	public static void cleanup() {
		cleanup(ormId);
	}
}