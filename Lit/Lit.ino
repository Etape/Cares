#include <PulseSensorPlayground.h>

#if defined(ESP32)
#include <WiFi.h>
#elif defined(ESP8266)
#include <ESP8266WiFi.h>
#endif

#include <FirebaseESP32.h>

#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

FirebaseData fbdo;
PulseSensorPlayground Freq;
PulseSensorPlayground Pouls;
LiquidCrystal_I2C lcd(0x27,16,2);  // set the LCD address to 0x27 for a 16 chars and 2 line display

// Set these to run example. 
#define FIREBASE_HOST "cares-cde43-default-rtdb.firebaseio.com" //https://cares-cde43-default-rtdb.firebaseio.com/ 
#define FIREBASE_AUTH "DpRbBGuQ8oGduuqPio9zVJfx5hOlS30jeRZO8ZvF"  //"DpRbBGuQ8oGduuqPio9zVJfx5hOlS30jeRZO8ZvF" 
#define WIFI_SSID "Xperia Z5_2290" 
#define WIFI_PASSWORD "Sauvage2" 
#define Ref "Lit" 

int Temp_sensor=0;
struct Parameters{    
    float temperature;
    float Frequence_cardiaque;
    float pouls;
    String etat;
};

int PinCardio=35, PinPouls=32,PinTemp=34;
float max_temp=39.00,max_Freq=80,max_pouls=80;
float min_temp=28.00,min_Freq=45,min_pouls=55;
int LV_Temp=16,LR_Temp=17,LV_Freq=5,LR_Freq=18,LV_pouls=19,LR_pouls=20;
int Buzzer=9;
Parameters  parameters={0,0,0," "};
boolean buzzer_state; 
const int delayMsec = 60;

int n = 0; 
bool state;
long tmp_blink;

void setup() {
  pinMode(LV_Temp,OUTPUT);
  pinMode(LR_Temp,OUTPUT);
  pinMode(LV_Freq,OUTPUT);
  pinMode(LR_Freq,OUTPUT);
  pinMode(LV_pouls,OUTPUT);
  pinMode(LR_pouls,OUTPUT);
  Pouls.analogInput(PinPouls);
  Freq.analogInput(PinCardio);
  if(!Pouls.begin() || !Freq.begin()){
    Serial.println("Capteurs non-demarrés");
  }
  //  ledcAttachPin(Buzzer, 1);
  // ledcSetup(1, 12000, 8);
  Serial.begin(115200); 
  // connect to wifi. 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD); 
  Serial.print("connecting"); 
  while (WiFi.status() != WL_CONNECTED) { 
    Serial.print("."); 
    delay(500); 
  } 
  Serial.println(); 
  Serial.print("connected: "); 
  Serial.println(WiFi.localIP());    
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);
  Firebase.setInt(fbdo,"Lit/LT394/buzzer",0);
  
  Serial.print("connected to Firebase ");
  tmp_blink=millis();

  LCDsetup();
}
 
int count=0,count2=0;
long temp;
String pathBuzzer="Lit/LT394/buzzer";

void loop(){
  buzzer_state=Firebase.getInt(fbdo,pathBuzzer);
  if(buzzer_state==1)
  {
    count++;
    state=false;
    if(count==1)
    temp=millis();
  }
  else{
    count=0;
    state=true;
  }
  if(millis()-temp>120000){ //2 minutes pour gerer l'alerte
    count2++;
    if(count2==1){
      state=true;
      Firebase.setInt(fbdo,"Lit/LT394/buzzer",0);
    }
   }
    
  Get_parameters();
  Upload_parameters(n); 
  Control_parameters(state);
  Afficher(parameters);
  
}


void Control_parameters(boolean buzzer_state){
  if(parameters.pouls==0 && parameters.Frequence_cardiaque==0 && parameters.temperature<27){
    parameters.etat="VIDE";
    digitalWrite(LV_Temp,HIGH);
    digitalWrite(LR_Temp,HIGH);
    digitalWrite(LV_Freq,HIGH);
    digitalWrite(LR_Freq,HIGH);
    digitalWrite(LV_pouls,HIGH);
    digitalWrite(LR_pouls,HIGH);
    
  }
  else if(parameters.temperature>=min_temp && parameters.temperature<=max_temp){
    digitalWrite(LV_Temp,HIGH);
    digitalWrite(LR_Temp,LOW);
    parameters.etat="BON";
  }
  else if(parameters.temperature<min_temp || parameters.temperature>max_temp){
    digitalWrite(LV_Temp,LOW);
    digitalWrite(LR_Temp,HIGH);
    parameters.etat="MAUVAIS";
  }
  else if(parameters.Frequence_cardiaque>=min_Freq && parameters.Frequence_cardiaque<=max_Freq){
    digitalWrite(LV_Freq,HIGH);
    digitalWrite(LR_Freq,LOW);
    //ledcDetachPin(Buzzer);
    parameters.etat="BON";
  }
  else if(parameters.Frequence_cardiaque<min_Freq || parameters.Frequence_cardiaque>max_Freq){
    digitalWrite(LV_Freq,LOW);
    digitalWrite(LR_Freq,HIGH);
    //if(buzzer_state)
    //ledcAttachPin(Buzzer, 1);
    //ledcWrite(1, 4);
    parameters.etat="CRITIQUE";
  }
  else if(parameters.pouls>=min_pouls && parameters.pouls<=max_pouls){
    digitalWrite(LV_pouls,HIGH);
    digitalWrite(LR_pouls,LOW);
    //ledcDetachPin(Buzzer);
    parameters.etat="BON";
  }
  else if(parameters.pouls<min_pouls || parameters.pouls>max_pouls){
    digitalWrite(LV_pouls,LOW);
    digitalWrite(LR_pouls,HIGH);
    //if(buzzer_state)
    //ledcAttachPin(Buzzer, 1);
    //ledcWrite(1, 4);
    parameters.etat="CRITIQUE";
  }
}


bool heartbeatDetected(int IRSensorPin, int Mdelay){
  static int maxValue = 0;
  static bool isPeak = false;
  int rawValue;
  bool result = false;
      
  rawValue = analogRead(IRSensorPin);
  rawValue *= (1000/Mdelay);
  
  if (rawValue * 4L < maxValue) {
    maxValue = rawValue * 0.8;
  }
  if (rawValue > maxValue - (1000/Mdelay)) {
    // Only change peak if we find a higher one.
    if (rawValue > maxValue) {
      maxValue = rawValue;
    }
    // Only return true once per peak.
    if (isPeak == false) {
      result = true;
    }
    isPeak = true;
  } 
  else if (rawValue < maxValue - (3000/Mdelay)) {
    isPeak = false;
    maxValue-=(1000/Mdelay);
  }
  return result;
}


float Get_Frequency(int analogPin){
 
  long tmp=millis();
  static int beatMsec = 0;
  float heartRateBPM = 0;
  while(millis()-temp<1000){ 
  if (heartbeatDetected(analogPin, delayMsec)) {
    heartRateBPM = 60000 / beatMsec;
    beatMsec = 0;
  }
  delay(delayMsec);
  beatMsec += delayMsec;
  }
  return heartRateBPM;
}


float Get_Temp(){
  int val1;
  val1=0;
  for(int i=0;i<100;i++){
  val1=val1+analogRead(PinTemp);
  delay(2);
  }
  
  float  temp= ( val1)/100;
  float   temperature=55-temp*0.008;
return temperature;
}


void Get_parameters(){
  parameters.temperature=Get_Temp();
  parameters.Frequence_cardiaque=Freq.getBeatsPerMinute();
  parameters.pouls=Pouls.getBeatsPerMinute();
}


void Upload_parameters(int n){
  // append a new value to /logs 
  String Name = "LT394";
      String Ref2="Lit";
      Ref2+="/";
      Ref2+=Name+"/";
      String RefTemp=Ref2+"temperature";
      String RefFreq=Ref2+"frequence_cardiaque";
      String RefPouls=Ref2+"pouls";
      Firebase.setString(fbdo,RefTemp,parameters.etat);
      delay(100);
      Firebase.setFloat(fbdo,RefTemp, parameters.temperature);
      delay(100);
      Firebase.setFloat(fbdo,RefFreq, parameters.Frequence_cardiaque);
      delay(100);
      Firebase.setFloat(fbdo,RefPouls, parameters.pouls);
}


void LCDsetup(){
  lcd.init();        
  // Print a message to the LCD.
  lcd.backlight();
  lcd.setCursor(3,0);
  lcd.print("Hello!");
  lcd.setCursor(2,1);
  lcd.print("CARES BED");
}


void Afficher(Parameters parameters){  
  String message1="";
  String message2="";

  message1+="T=";
  message1+=parameters.temperature;
  message1+=" °C";
  
  message1+="F=";
  message1+=parameters.Frequence_cardiaque;
  message1+=" bpm";
  
  message2+="P=";
  message2+=parameters.pouls;
  message2+=" pouls";
  
  message2+=parameters.etat;

  lcd.clear();
  lcd.backlight();
  lcd.setCursor(2,0);
  lcd.print(message1);
  lcd.setCursor(2,1);
  lcd.print(message2);

  Serial.print(message1);
  Serial.print("***");
  Serial.println(message2);
  
}



