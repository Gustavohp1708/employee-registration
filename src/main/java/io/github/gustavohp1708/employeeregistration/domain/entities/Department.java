package io.github.gustavohp1708.employeeregistration.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.text.Normalizer;
import java.util.Locale;

public enum Department {

    ADMINISTRACAO("ADMINISTRAÇÃO"),
    COMERCIAL("COMERCIAL"),
    COMPRAS("COMPRAS"),
    CONTABILIDADE("CONTABILIDADE"),
    DEPARTAMENTO_PESSOAL("DEPARTAMENTO PESSOAL"),
    FINANCEIRO("FINANCEIRO"),
    JURIDICO("JURÍDICO"),
    LOGISTICA("LOGÍSTICA"),
    MARKETING("MARKETING"),
    OPERACOES("OPERAÇÕES"),
    PLANEJAMENTO_ESTRATEGICO("PLANEJAMENTO ESTRATÉGICO"),
    RECURSOS_HUMANOS("RECURSOS HUMANOS"),
    RELACOES_PUBLICAS("RELAÇÕES PÚBLICAS"),
    TECNOLOGIA_DA_INFORMACAO("TECNOLOGIA DA INFORMAÇÃO"),
    VENDAS("VENDAS");

    private final String description;

    Department(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Department fromValue(String value) {
        if (value == null) {
            return null;
        }

        var normalizedValue = normalize(value);
        for (var department : values()) {
            if (normalize(department.name()).equals(normalizedValue)
                    || normalize(department.description).equals(normalizedValue)) {
                return department;
            }
        }

        throw new IllegalArgumentException("Invalid department: " + value);
    }

    private static String normalize(String value) {
        return Normalizer.normalize(value.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace(' ', '_')
                .toUpperCase(Locale.ROOT);
    }
}
