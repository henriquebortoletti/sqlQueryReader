package com.sql.query.reader.sqlQueryReader.parser;

import com.sql.query.reader.sqlQueryReader.reader.Reader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class JsonToSQLTest {

    @Test
    public void firstProblemTests() throws IOException {
        String sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/first-query.json")).parse();
        Assert.assertEquals("SELECT ProductId,ProductName FROM Products WHERE ProductId = ALL( SELECT ProductID FROM OrderDetails WHERE Quantity = 10);", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/second-query.json")).parse();
        Assert.assertEquals("SELECT * FROM Customers WHERE City = \"London\" AND Country = \"UK\";", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/third-query.json")).parse();
        Assert.assertEquals("SELECT * FROM Products WHERE Price BETWEEN 50 AND 60;", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/forth-query.json")).parse();
        Assert.assertEquals("FROM Suppliers WHERE EXISTS( SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20);", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/fifth-query.json")).parse();
        Assert.assertEquals("SELECT * FROM Customers WHERE City IN ('Paris','London');", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/firstProblem/sixth-query.json")).parse();
        Assert.assertEquals("SELECT * FROM Customers WHERE City NOT LIKE ('s%');", sql);
    }

    @Test
    public void secondProblemTests() throws IOException {
        String sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/secondProblem/first-query.json")).parse();
        Assert.assertEquals("SELECT Orders.OrderID,Customers.CustomerName FROM Orders INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID;", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/secondProblem/second-query.json")).parse();
        Assert.assertEquals("SELECT Customers.CustomerName,Orders.OrderID FROM Customers LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID;", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/secondProblem/third-query.json")).parse();
        Assert.assertEquals("SELECT Orders.OrderID,Employees.LastName,Employees.FirstName FROM Orders RIGHT JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID;", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/secondProblem/forth-query.json")).parse();
        Assert.assertEquals("SELECT Customers.CustomerName,Orders.OrderID FROM Customers FULL OUTER JOIN Orders ON Customers.CustomerID = Orders.CustomerID;", sql);

        sql = new JsonToSQL(Reader.getInstance().read("src/test/resources/secondProblem/fifth-query.json")).parse();
        Assert.assertEquals("SELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City FROM Customers A, Customers B WHERE A.CustomerID <> B.CustomerID AND A.City = B.City;", sql);
    }

}
