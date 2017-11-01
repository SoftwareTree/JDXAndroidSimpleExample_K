﻿# JDXAndroidSimpleExample_K
### A Simple Kotlin Example Project using JDXA ORM for Android

This project shows a simple Android app, written in the Kotlin language, that uses JDXA ORM to exchange object data with an on-device SQLite relational database.

![JDXA_Kotlin Picture](https://softwaretree.com/v1/images/JDXA_Kotlin.png)  

#### Some highlights:  
*	The object model consists of one Kotlin class (ClassA). 
*	The mapping specification (in the file .../res/raw/simple_example.jdx) is simple, intuitive, non-intrusive, and succinct.
*	API calls for CRUD operations are simple. 
*	JDXA provides handy utility methods for displaying an object or a list of objects.  

#### This project is similar to the [JDXAndroidSimpleExample](https://github.com/SoftwareTree/JDXAndroidSimpleExample) project that uses POJO domain model objects. The same mapping specification that is non-intrusive to the object class definitions works for both the Java and the Kotlin domain model classes. The JDXAndroidSimpleExample project is also presented in a video format on YouTube at https://youtu.be/g9pE6ocTDvU  

#### To run this app in your own setup, please do the following:
*	Clone this project on your desktop.
*	Get the JDXA SDK download instructions from [this link](http://softwaretree.com/v1/products/jdxa/download-jdxa.php).
*	You may download just the mini version of the SDK.
*	Add the libraries (JDXAndroid-nn.n.jar and sqldroid.jar) from the SDK to the app/libs directory and build the project.
*	Run the app.  

### About JDXA ORM 
JDXA is a simple yet powerful, non-intrusive, flexible, and lightweight Object-Relational Mapping (ORM) product that simplifies and accelerates the development of Android apps by providing intuitive, object-oriented access to on-device relational (e.g., SQLite) data.  

Adhering to some well thought-out [KISS (Keep It Simple and Straightforward) principles](http://softwaretree.com/v1/KISSPrinciples.html), JDXA boosts developer productivity and reduces maintenance hassles by eliminating endless lines of tedious SQL code.  

#### Some of the powerful and practical features of JDXA include: 
*	Declarative mapping specification between an object model and a relational model is done textually using a simple grammar (no XML complexity). 
*	Full flexibility in domain object modeling – one-to-one, one-to-many, and many-to-many relationships as well as class-hierarchies supported.
*	POJO (Plain Old Java Objects) and Kotlin friendly non-intrusive programming model, which does not require you to change your Java classes in any way:   

    - No need to subclass your domain model classes from any base class
    - No need to clutter your source code with annotations
    - No source code generation (No need for DAO classes)
    - No pre-processing or post-processing of your code  

*	Support for persistence of JSON objects.
*	A small set of intuitive APIs for object persistence.
*	Automatic generation of relational schema from an object model. 
*	A highly optimized metadata-driven ORM engine that is lightweight, dynamic, and flexible.   

JDXA ORM is a product of Software Tree. To get more information and a free trial version of JDXA SDK, please visit http://www.softwaretree.com.  

JDXA is used with the SQLDroid open source library. SQLDroid is provided under the licensing terms mentioned [here](https://github.com/SQLDroid/SQLDroid/blob/master/LICENSE).



