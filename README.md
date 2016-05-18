# SmartHouse
Git for SmartHouse example, for INSA of TOULOUSE 's project

For the Android application, you will need the latest Android Studio version (at least 2.0) in order to edit the project.

The Folders in the root directory of this project contains:

	Application:
		contains all the executable jars and the scripts to run.
		You can basically copy and paste this folder on the target (ex: Raspberry) and run ./FrameselfManager.sh
		Beware of the IP address, you will need to change it.
		
	CustomFrameself:
		contains a decompiled version of Frameself, including an interface to retrieve the different rule names.
		
	Diagrammes:
		contains the different class diagrams of the main classes.
		
	FrameselfManager:
		contains the source of the program that will start & stop the frameself server.

	GalileoApplication:
		contains the source code for the Galileo, for both event sending and action fetching parts.
		
	SmartControl:
		contains the source code of the Android application, created with Android Studio 2.1
		
	Tablea_Led:
		contains the basic API to interact with the LED Matrix.
