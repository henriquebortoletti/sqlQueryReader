package com.sql.query.reader.sqlQueryReader.parser;

import com.sql.query.reader.sqlQueryReader.model.*;
import com.sql.query.reader.sqlQueryReader.reader.Reader;

import java.util.*;

public class JsonToSQL {

    private final Query json;
    private final StringBuilder sb;
    private static final String ON = "ON";

    public JsonToSQL(Query json) {
        this.json = json;
        sb = new StringBuilder();
    }

    public String parse() {
        parseQuery(json);
        sb.append(";");
        return sb.toString().trim();
    }

    private void parseQuery(Query query) {
        parseColumns(Structure.SELECT, query.getSelect());
        parseColumns(Structure.FROM, query.getFrom());
        parseColumns(Structure.WHERE, query.getWhere());
        parseJoin(query.getJoin());
    }

    private void parseJoin(Join join) {
        if (join != null) {
            appendWithLeftSpace(join.getType().toUpperCase(Locale.ROOT));
            appendWithLeftSpace(Structure.JOIN.toString());
            appendWithLeftSpace(join.getTable());
            appendWithLeftSpace(ON);
            parseColumns(join.getOn());
        }
    }

    private void parseColumns(Structure structure, Columns columns) {
        if (columns != null) {
            appendWithLeftSpace(structure.toString());
            parseColumns(columns);
        }
    }

    private void parseColumns(Columns columns) {
        Iterator<Column> iterator = columns.getColumns().iterator();
        while (iterator.hasNext()) {
            parseColumn(iterator.next());
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
    }

    private void parseColumn(Column column) {
        parseField(column.getFieldName(), false);
        parseOperator(column.getOperator());
        parseField(column.getFieldValue(), true);
    }

    private void parseField(Object fieldValue, boolean isFieldValue) {
        if (fieldValue instanceof List) {
            if (isFieldValue) {
                sb.append(" (");
                sb.append(String.join(",", (List) fieldValue));
                sb.append(")");
            } else {
                appendWithLeftSpace(String.join(",", (List) fieldValue));
            }
        } else if (fieldValue instanceof String) {
            appendWithLeftSpace((String) fieldValue);
        } else if (fieldValue instanceof Integer) {
            appendWithLeftSpace(Integer.toString((Integer) fieldValue));
        } else if (fieldValue instanceof Double) {
            appendWithLeftSpace(Double.toString((Double) fieldValue));
        } else if (fieldValue instanceof HashMap) {
            Map<String, Object> fieldMap = (Map) fieldValue;
            try {
                Column fieldColumn = Reader.getInstance().readColumn(fieldMap);
                parseColumn(fieldColumn);
            } catch (IllegalArgumentException e) {
                sb.append("(");
                Query query = Reader.getInstance().readQuery(fieldMap);
                parseQuery(query);
                sb.append(")");
            }
        }
    }

    private void parseOperator(String operator) {
        if (operator != null && !operator.isEmpty()) {
            String symbol = operator.toUpperCase(Locale.ROOT);
            if (Operator.getOperator(symbol).isPresent()) {
                appendWithLeftSpace(Operator.getOperator(symbol).get().getSymbol());
            } else {
                appendWithLeftSpace(symbol);
            }
        }
    }

    private void appendWithLeftSpace(String string) {
        if (string != null && !string.isEmpty()) {
            sb.append(" ").append(string);
        }
    }

    private enum Structure {
        SELECT, FROM, WHERE, JOIN
    }

}
