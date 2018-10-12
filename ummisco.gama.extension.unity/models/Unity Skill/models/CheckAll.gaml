model CheckAll

global {
/** Insert the global definitions, variables and actions here */

	init{
		create CheckingAgent number: 1;
	}
}

 




species CheckingAgent skills: [unity]{
	string playerName <- "Player0";
	
	aspect default {
		draw circle(1) color:#red;
	}
	
	init{
		 do connectMqttClient();
		 write "connected";
	}
	
	reflex contactUnity when: cycle = 1
	{
		
		do subscribe_To_Topic topic:"Gama";
		do subscribe_To_Topic topic:"replay";
		do subscribe_To_Topic topic:"notification";
		
		//TODO: Add support for notification frequency.
		do unityNotificationSubscribe notificationId: "Notification_01" objectName: playerName fieldType: "field" fieldName: "count" fieldValue:10 fieldOperator: "==";
	
		do getUnityField objectName: playerName attribute:"speed"; 											    // getUnityField
		
		do callUnityMonoAction objectName: playerName   actionName:"setWinText"  attribute:" --- Game ON --- "; // monoActionTopic
		
		map<string,string> AtList <- ["s"::50, "count"::"Count text", "win"::"Win text"];
		do callUnityPluralAction objectName: playerName actionName:"changeAllAttributes"  attributes:AtList; 	// callUnityPluralAction
	
		do setUnityFields objectName: playerName attributes: map<string, string>(["speed"::35]);				// setUnityFields
	
		do setUnityFields objectName: playerName attributes: map<string, string>(["count"::10]);				// setUnityFields
		
		
		//TODO: Add scale as optionnal 
		do newUnityObject objectName: "EastWall" type:"Cube" color:rgb(255,1,144) position: {12,1,0};			// newUnityObject
	
		do setUnityProperty objectName: "EastWall" propertyName:"localScale" propertyValue:{25.0,2.0,0.3};		// setUnityProperty
	
		do setUnityProperty objectName: "EastWall" propertyName:"localEulerAngles" propertyValue:{0,90,0};		// setUnityProperty
	
		do setUnityProperty objectName: "EastWall" propertyName:"detectCollisions" propertyValue:false;			// setUnityProperty
	
		do callUnityMonoAction objectName: playerName actionName:"setSpeed"  attribute:string(50); 				// callUnityMonoAction
	
		do callUnityMonoAction objectName: playerName actionName:"setSpeed"  attribute:60; 						// callUnityMonoAction
	
		do setUnityColor objectName: playerName color: rgb(0,100,0);											// setUnityColor
	
		do setUnityPosition objectName: playerName position: {4,1,9};											// setUnityPosition
	
		do unityMove objectName: playerName position: {4,0,7} speed: 5 smoothMove: false;						// unityMove
		
		do unityMove objectName: playerName position: {-2,0,-2} speed: 1 smoothMove: true;						// unityMove
		
		
		
		write "messge sent";
	}
	
	reflex destroyObject when: cycle = 8{
		
		string fieldValue <- get_unity_replay(); 																// get_unity_replay
		write "Field value is "+fieldValue;
		
		bool isNotifiyed <- isNotificationTrue("Notification_01");												// isNotifiyed
		write "Notification Statut "+isNotifiyed;
		
		string mes <- get_unity_notification();	
		write "Notification Message (if exist) "+mes;															// get_unity_notification
		
		
		//do destroyUnityObject objectName: playerName;							// destroyUnityObject
		string msg<- getAllActionsMessage();
		write msg; 															
	}
}


experiment CheckAll type:gui {
/** Insert here the definition of the input and output of the model */
	output {
		display Dp1 type:opengl{
			species CheckingAgent;
		}		
	}
}