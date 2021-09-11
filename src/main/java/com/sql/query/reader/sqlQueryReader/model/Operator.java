package com.sql.query.reader.sqlQueryReader.model;

import java.util.Arrays;
import java.util.Optional;

public enum Operator {
    IN("IN"), LIKE("LIKE"), EQUAL("="), LESS("<"), LESS_THAN_OR_EQUAL("<="),
    GREATER_THAN_OR_EQUAL(">="), DIFFERENT("<>"), BETWEEN("BETWEEN"), NOT("NOT"),
    STAR("*"), EXISTS("EXISTS");

    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Optional<Operator> getOperator(String symbol) {
        return
                Arrays.stream(Operator.values())
                        .filter(operator -> operator.toString().equals(symbol))
                        .findFirst();
    }
}
