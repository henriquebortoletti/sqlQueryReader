package com.sql.query.reader.sqlQueryReader;

import com.sql.query.reader.sqlQueryReader.model.Query;

import com.sql.query.reader.sqlQueryReader.parser.JsonToSQL;
import com.sql.query.reader.sqlQueryReader.reader.Reader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader reader = Reader.getInstance();
        Query query = reader.read(args[0]);
        System.out.println("SQL QUERY:");
        System.out.println(new JsonToSQL(query).parse());
    }
}
