package com.sql.query.reader.sqlQueryReader.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Column {

    private String operator;

    private Object fieldName;

    private Object fieldValue;

}
