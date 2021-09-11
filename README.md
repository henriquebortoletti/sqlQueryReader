#Transform Json to SQL

In this version it is supported sql queries that are composed with *select*, *from*, *where*, all kind of *joins* and all *operators*

Below is an example of a simple query in the Json format:

SQL Query:

`SELECT * FROM Customers WHERE City = "London" AND Country = "UK";`

Json format:

```
{
  "select": {
    "columns": [
      {
        "operator": "STAR"
      }
    ]
  },
  "from": {
    "columns": [
      {
        "fieldName": "Customers"
      }
    ]
  },
  "where": {
    "columns": [
      {
        "operator": "Equal",
        "fieldName": "City",
        "fieldValue": "\"London\""
      },
      {
        "operator": "Equal",
        "fieldName": "Country",
        "fieldValue": "\"UK\""
      }
    ]
  }
}
```

The syntax used to develop the system is based on W3C specifications https://www.w3schools.com/sql/

To execute the program it is necessary that the maven is installed and the version 8 of java

On terminal type the following command to run the program:
``
mvn compile exec:java -Dexec.mainClass="com.sql.query.reader.sqlQueryReader.Main" -Dexec.args="<PATH_TO_JSON>"
``

Replace the string ``<PATH_TO_JSON>`` on ``-Dexec.args`` for the path of your json 