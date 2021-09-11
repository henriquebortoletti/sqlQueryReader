package com.sql.query.reader.sqlQueryReader.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Query {

    private Columns select;

    private Columns where;

    private Columns from;

    private Join join;

}
