package com.softwaretree.jdxandroidsimpleexample

import java.text.SimpleDateFormat
import java.util.TimeZone

import com.softwaretree.jdx.JDXHelper
import com.softwaretree.jdx.JDXSetup
import com.softwaretree.jdxandroid.DatabaseAndJDX_Initializer
import com.softwaretree.jdxandroid.Utils
import com.softwaretree.jdxandroidsimpleexample.model.ClassA
import com.softwaretree.jx.JXUtilities

import android.app.Activity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import android.widget.Toast

/**
 * This project exemplifies how JDXA ORM and associated utilities can be used to easily develop,
 * in the Kotlin language, an Android app that exchanges data of domain model objects with
 * an SQLite database. In particular, this project demonstrates the following:
 *
 *
 * 1) How an ORM Specification (mapping) for domain model classes written in Kotlin can be
 * defined textually using simple statements. The mapping is specified in a text file
 * \res\raw\simple_example.jdx identified* by the resource id R.raw.simple_example.
 *
 *
 * 2) Use of [AppSpecificJDXSetup] and [DatabaseAndJDX_Initializer] classes to easily:
 * &nbsp;&nbsp;&nbsp;&nbsp;  a) create the underlying database, if not already present.
 * &nbsp;&nbsp;&nbsp;&nbsp;  b) create the schema (tables and constraints) corresponding
 * to the JDXA ORM specification every time the application runs.
 * See setForceCreateSchema(true) in [AppSpecificJDXSetup.initialize].
 *
 * 3) Examples of how just a few lines of object-oriented code incorporating JDX APIs
 * can be used to easily interact with relational data.  This avoids tedious and
 * time-consuming coding/maintenance of low-level SQL statements.
 *
 *
 * 4) Example of how a [JDXHelper] facade object can be used to interact with relational
 * data using simpler methods.
 *
 *
 * 5) Examples of how details of an object or a list of objects can be added in JDX log and
 * how that output can be collected in a file and then displayed in a scrollable TextBox.
 *
 *
 * @author Damodar Periwal
 */
class JDXAndroidSimpleExampleActivity : Activity() {
    internal var jdxSetup: JDXSetup? = null

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        this.title = resources.getString(R.string.activity_title)

        val tvJDXLog = findViewById(R.id.tvJDXLog) as TextView
        tvJDXLog.movementMethod = ScrollingMovementMethod()

        try {
            AppSpecificJDXSetup.initialize()  // must be done before calling getInstance()
            jdxSetup = AppSpecificJDXSetup.getInstance(this)

            // Use a JDXHelper object to easily configure capturing of JDX log output
            val jdxHelper = JDXHelper(jdxSetup)
            //val jdxLogFileName = filesDir + "jdx.log"
            val jdxLogFileName = filesDir.path + System.getProperty("file.separator") + "jdx.log"
            jdxHelper.setJDXLogging(jdxLogFileName)

            useJDXORM(jdxHelper)

            jdxHelper.resetJDXLogging()

            // Show the captured JDX log on the screen
            tvJDXLog.text = Utils.getTextFileContents(jdxLogFileName)

        } catch (ex: Exception) {
            Toast.makeText(baseContext, "Exception: " + ex.message, Toast.LENGTH_SHORT).show()
            cleanup()
            return
        }

    }

    /**
     * Do the necessary cleanup.
     */
    public override fun onDestroy() {
        super.onDestroy()
        cleanup()
    }

    private fun cleanup() {
        AppSpecificJDXSetup.cleanup() // Do this when the application is exiting.
        jdxSetup = null
    }

    /**
     * Shows some simple examples of using JDXA ORM APIs to exchange object data with a relational database.
     *
     *
     * @param jdxHelper A helper object to further simplify interactions with the JDXA ORM system.
     * *
     * @throws Exception
     */
    // @Throws(Exception::class)
    private fun useJDXORM(jdxHelper: JDXHelper?) {
        if (null == jdxHelper) {
            return
        }

       //val aClassName = ClassA::class.java!!.getName()
        val aClassName = ClassA::class.java.name
        val dfm = SimpleDateFormat("yyyy-MM-dd")
        dfm.timeZone = TimeZone.getTimeZone("America/Los_Angeles")

        // First delete all existing ClassA objects from the database.
        JXUtilities.log("\n-- First deleting all the existing ClassA objects from the database --\n")
        jdxHelper.delete2(aClassName, null)

        // Create and save a new ClassA object with aId=1
        JXUtilities.log("\n-- Creating and saving three ClassA objects (A1, A2, and A3) in the database --\n")
        var aObject = ClassA(1, "A1", dfm.parse("1981-01-01"), true, 1.1.toFloat())
        jdxHelper.insert(aObject, false)

        // Create and save a new ClassA object with aId=2
        aObject = ClassA(2, "A2", dfm.parse("1982-02-02"), false, 2.2.toFloat())
        jdxHelper.insert(aObject, false)

        // Create and save a new ClassA object with aId=3
        aObject = ClassA(3, "A3", dfm.parse("1983-03-03"), false, 3.3.toFloat())
        jdxHelper.insert(aObject, false)

        // Retrieve all the ClassA objects from the database
        JXUtilities.log("\n-- getObjects for all the ClassA objects --\n")
        var queryResults: List<*> = jdxHelper.getObjects(aClassName, null)
        JXUtilities.printQueryResults(queryResults)

        // Retrieve all the ClassA objects from the database in the descending order of aId
        JXUtilities.log("\n-- getObjects for all the ClassA objects in the descending order of aId --\n")
        queryResults = jdxHelper.getObjects(aClassName, "ORDER BY aId DESC")
        JXUtilities.printQueryResults(queryResults)

        // Retrieve all the ClassA objects from the database with a search condition (predicate)
        JXUtilities.log("\n-- getObjects for all the ClassA objects with a search condition of aFloat > 1.5 --\n")
        queryResults = jdxHelper.getObjects(aClassName, "aFloat > 1.5")
        JXUtilities.printQueryResults(queryResults)

        // Retrieve all the ClassA objects from the database with a search condition (predicate) and in a certain order
        JXUtilities.log("\n-- getObjects for all the ClassA objects with a search condition of aFloat > 1.5 --\n")
        queryResults = jdxHelper.getObjects(aClassName, "aFloat > 1.5 ORDER BY aDate DESC")
        JXUtilities.printQueryResults(queryResults)

        // Retrieve the ClassA object with aId=2
        JXUtilities.log("\n-- getObjectById for aId=2 --\n")
        aObject = jdxHelper.getObjectById(aClassName, "aId=2", false, null) as ClassA
        JXUtilities.printObject(aObject, 0, null)

        // Change some attributes of the retrieved object and update it in the database
        aObject.aBoolean = true
        aObject.aFloat = 2.22.toFloat()
        jdxHelper.update(aObject, false)

        // Retrieve the updated ClassA object with aId=2
        JXUtilities.log("\n-- getObjectById for aId=2 after updating the object --\n")
        aObject = jdxHelper.getObjectById(aClassName, "aId=2", false, null) as ClassA
        JXUtilities.printObject(aObject, 0, null)

        // Delete the just updated object with aId=2
        JXUtilities.log("\n-- Deleting the object with aId=2 --\n")
        jdxHelper.delete(aObject, false)

        // Retrieve all the objects
        JXUtilities.log("\n-- getObjects for all the ClassA objects --\n")
        queryResults = jdxHelper.getObjects(aClassName, null)
        JXUtilities.printQueryResults(queryResults)

        return
    }
}