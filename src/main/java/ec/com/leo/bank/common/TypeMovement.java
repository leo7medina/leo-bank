package ec.com.leo.bank.common;

import lombok.Getter;

@Getter
public enum TypeMovement {

    DEBIT("DEBITO"),
    CREDIT("CREDITO");

    private final String value;
    TypeMovement(String value) {
        this.value = value;
    }
}
