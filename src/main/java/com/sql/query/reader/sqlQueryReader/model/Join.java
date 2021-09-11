package com.sql.query.reader.sqlQueryReader.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Join {

    private String type;

    private String table;

    private Columns on;

}
