
# Parking Lot Application

This Applications lets user 
1. create a new parking lot . 
2. Park a vehicle . 
3. Unpark a vehicle 
4. Get the status of the parking lot 

The user can run The application via below command 

Navigate to the directory where the folder is then 

java -jar .\target\ParkingLot-0.0.1-SNAPSHOT.jar "input_file_path"


Example::<br />
java -jar .\target\ParkingLot-0.0.1-SNAPSHOT.jar "C:\Users\Downloads\parking_lot.txt"


Sample Data 

create 6<br />
park KA-01-HH-1234<br />
park KA-01-HH-9999<br />
park KA-01-BB-0001<br />
park KA-01-HH-7777<br />
park KA-01-HH-2701<br />
park KA-01-HH-3141<br />
leave KA-01-HH-3141 4<br />
status<br />
park KA-01-P-333<br />
park DL-12-AA-9999<br />
leave KA-01-HH-1234 4<br />
leave KA-01-BB-0001 6<br />
leave DL-12-AA-9999 2<br />
park KA-09-HH-0987<br />
park CA-09-IO-1111<br />
park KA-09-HH-0123<br />
status<br />


Also attached a sample file named  "parking_lot.txt"