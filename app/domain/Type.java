package domain;

import io.vavr.collection.List;

public enum Type {
    UNKNOWN("Desconocido"),
    CONFERENCIA("Conferencia"),
    SEMINARIO("Seminario"),
    CONGRESO("Congreso"),
    CURSO("Curso");

    private String name;

    Type(String name){
        this.name = name;
    }

    public static Type indexOf(String name) {
        return List.of(Type.values()).find(t -> t.getName().equals(name)).getOrElse(Type.UNKNOWN);
    }

    public String getName() {
        return name;
    }

}
