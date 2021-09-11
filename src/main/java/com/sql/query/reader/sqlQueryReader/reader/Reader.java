package com.sql.query.reader.sqlQueryReader.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sql.query.reader.sqlQueryReader.model.Column;
import com.sql.query.reader.sqlQueryReader.model.Query;

import java.io.*;
import java.util.Map;

public class Reader {

    private static Reader instance;

    private ObjectMapper mapper;

    private Reader() {
        this.mapper = new ObjectMapper();
    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public Query read(String path)
            throws IOException {

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return mapper.readValue(resultStringBuilder.toString(), Query.class);
    }

    public Query readQuery(Map<String, Object> map) {
        return mapper.convertValue(map, Query.class);
    }

    public Column readColumn(Map<String, Object> map) {
        return mapper.convertValue(map, Column.class);
    }

}
