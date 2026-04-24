# Smart Campus API

## Overview

This project is a REST API built using JAX-RS for managing a Smart Campus system.

The system allows you to:
- create and manage rooms
- assign sensors to rooms
- record sensor readings
- prevent invalid actions (like deleting rooms that still have sensors)

All data is stored in memory using Java collections (HashMap / ArrayList), as required.

Base URL:
http://localhost:8081/api/v1

---

## How to Run

1. Open the project in NetBeans  
2. Click Run  
3. Wait for the server to start  
4. Open in browser or Postman:

http://localhost:8081/api/v1

---

## Main Endpoints

### Discovery

GET /api/v1  

Returns basic API info and links to main resources.

---

## Rooms

### Create Room

POST /api/v1/rooms  

Example:

```json
{
  "id": "R1",
  "name": "Library Room",
  "capacity": 100
}

Returns 201 Created.

Get All Rooms

GET /api/v1/rooms

Get Room by ID

GET /api/v1/rooms/R1

Delete Room

DELETE /api/v1/rooms/R1

If the room still has sensors, it will return 409 Conflict.
This is to stop data becoming inconsistent.

Sensors
Create Sensor

POST /api/v1/sensors

{
  "id": "S1",
  "type": "Temperature",
  "status": "ACTIVE",
  "roomId": "R1"
}

The API checks if the room exists before creating the sensor.

Get Sensors

GET /api/v1/sensors

Filter Sensors

GET /api/v1/sensors?type=Temperature

Used to filter sensors by type.

Sensor Readings
Add Reading

POST /api/v1/sensors/S1/readings

{
  "id": "READ1",
  "timestamp": 1710000000,
  "value": 25.5
}

Also updates the sensor’s current value.

Get Readings

GET /api/v1/sensors/S1/readings

Error Handling

The API includes custom error handling so it doesn’t expose raw server errors.

Examples:

409 → trying to delete a room that still has sensors
422 → creating a sensor with a room that doesn’t exist
403 → adding a reading to a sensor in maintenance
500 → unexpected server error
Example curl Commands
Get API info
curl http://localhost:8081/api/v1
Create Room
curl -X POST http://localhost:8081/api/v1/rooms ^
-H "Content-Type: application/json" ^
-d "{\"id\":\"R1\",\"name\":\"Library Room\",\"capacity\":100}"
Get Rooms
curl http://localhost:8081/api/v1/rooms
Create Sensor
curl -X POST http://localhost:8081/api/v1/sensors ^
-H "Content-Type: application/json" ^
-d "{\"id\":\"S1\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"roomId\":\"R1\"}"
Add Reading
curl -X POST http://localhost:8081/api/v1/sensors/S1/readings ^
-H "Content-Type: application/json" ^
-d "{\"id\":\"READ1\",\"timestamp\":1710000000,\"value\":25.5}"

FAQs


Resource Lifecycle

JAX-RS usually creates a new instance of a resource for each request.
Because of this, I didn’t store data inside the resource classes.
Instead, I used a shared DataStore class to keep everything in memory.

Hypermedia (HATEOAS)

The discovery endpoint includes links to other endpoints like rooms and sensors.
This helps users understand how to use the API without checking documentation all the time.

IDs vs Full Objects

Returning only IDs uses less data but requires more requests.
Returning full objects is easier for the client but uses more bandwidth.
In this project I returned full objects because the data is small.

DELETE Idempotency

DELETE is idempotent because deleting the same resource multiple times results in the same final state.
If the room is already deleted, the result doesn’t change.

JSON Only (@Consumes)

If the client sends something that is not JSON, the request will fail.
This helps make sure the API only accepts the correct format.

Query Param vs Path

Using query params like ?type=CO2 is better for filtering.
It keeps the endpoint simple and allows adding more filters later.

Sub-Resource Locator

I used a separate class for sensor readings.
This keeps the code cleaner instead of putting everything in one file.

422 vs 404

422 is better when the request is valid but contains incorrect data (like a wrong roomId).
404 is for when the endpoint itself doesn’t exist.

Stack Trace Risk

If stack traces are shown, attackers can see internal details of the system.
That’s why I used a global exception handler.

Logging

I used a logging filter instead of adding logs everywhere.
This keeps the code cleaner and easier to manage.

Video
Part 1:
(https://www.loom.com/share/312daa0b8b554fe680251ad56f7c194f)

Part 2:
(https://www.loom.com/share/b55525cdeb4548f4889be39daa975fe2)

Part 3:
(https://www.loom.com/share/e1b0dcee36794424a94082daa1b152b7)
In loom record video, I demonstrated all endpoints using Postman, including success and error cases.
