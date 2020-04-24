# Demonstration Guide

## 1. Clone the group repo to a new folder.
```
git clone https://github.com/isel-leic-ls/1920-2-LI42D-G09.git
```

```
cd 1920-2-LI42D-G09
```

## 2. Checkout the phase 2 tag (0.2.* tag).

```
git checkout tags/0.2.0
```

## 3. Use gradle to clean and build the project.

```
gradlew clean
```
```
gradlew build
```

## 4. Use gradle to clean and build the project again.

```
gradlew clean
```
```
gradlew build
```

## 5. Run OPTION / command directly from the console.

```
"C:\Program Files\Java\jdk-11.0.7\bin\java.exe" -classpath C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\build\classes\java\main;C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\vendor\main/* pt.isel.ls.App OPTION /
```

## 6. List rooms directly from the console.

```
"C:\Program Files\Java\jdk-11.0.7\bin\java.exe" -classpath C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\build\classes\java\main;C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\vendor\main/* pt.isel.ls.App GET /rooms
```

## 7. Start the application without any parameters.

```
"C:\Program Files\Java\jdk-11.0.7\bin\java.exe" -classpath C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\build\classes\java\main;C:\Users\Tiago\Desktop\LS_DEMO\1920-2-LI42D-G09\vendor\main/* pt.isel.ls.App
```

## 8. Create two labels projetor and ac.

```
POST /labels name=projetor
```

```
POST /labels name=ac
```

## 9. List all labels in text/plain.

```
GET /labels
```

## 10. Create two rooms, one with the two labels ``(LS1)`` and another with the label projetor and a capacity of twenty ``(LS2)``.

```
POST /rooms name=LS1&location=Building+F&capacity=50&description=descriptionForLS1&label=projetor&label=ac
```

```
POST /rooms name=LS2&location=Building+F&capacity=20&description=descriptionForLS2&label=ac
```

## 11. Get the details of the room with two labels ``(LS1)`` in text/html format and output it to a file. Open the resulting file in a browser.

```   
GET /rooms/LS1 accept:text/html|file-name:getLS1details.html
```

```
start getLS1details.html
```

## 12. List all rooms, for each one of the following formats:

### 1. ```text/plain```

```
GET /rooms
```

### 2. ```text/html```

```
GET /rooms accept:text/html|file-name:getRooms.html
```

```
start getRooms.html
```

## 13. Create user Miguel.

```
POST /users name=Miguel&email=miguelexemplo@isel.pt
```

## 14. Try to create a booking with a duration of two hours and four minutes.

```
POST /rooms/LS2/bookings uid=miguelexemplo@isel.pt&begin=2020-05-01+10:30:00&duration=124
```

## 15. Create a booking for today with a duration of two hours for Miguel.
    
```
POST /rooms/LS2/bookings uid=miguelexemplo@isel.pt&begin=2020-05-01+10:30:00&duration=120
```

## 16. Try to create a booking for Miguel that overlaps the previous one.

```
POST /rooms/LS2/bookings uid=miguelexemplo@isel.pt&begin=2020-05-01+11:30:00&duration=120
```

## 17.Create a booking for tomorrow with a duration of thirty minutes in the room with two labels``(LS1)`` for Miguel ``SAVE BID!``.

```
POST /rooms/LS1/bookings uid=miguelexemplo@isel.pt&begin=2020-05-02+09:30:00&duration=30
```

## 18. List all bookings of Miguel in text/plain.

```
GET /users/miguelexemplo@isel.pt/bookings
```
## 19.Create a room ``(LS3)`` with a capacity of twenty and the label projetor.

```
POST /rooms name=LS3&location=Building+F&capacity=20&description=descriptionForLS3&label=projetor
```

## 20. List all rooms with the labels projetor and ac.

```
GET /rooms label=projetor&label=ac
```

## 21. List all rooms with the label projetor and a capacity of twenty.

```
GET /rooms label=projetor&capacity=20
```

## 22. List all rooms available on the period starting at tomorrow with a duration of thirty minutes with the label projetor.

```
GET /rooms label=projetor&begin=2020-05-02+00:00:00&duration=30
```

## 23. Delete the last created booking ``(Booking ID Required!)``.

```
DELETE /rooms/LS1/bookings/??? <- BOOKING ID
```

## 24. List all bookings of Miguel.

```
GET /users/miguelexemplo@isel.pt/bookings
```

## 25. Exit the application.

```
EXIT /
```


