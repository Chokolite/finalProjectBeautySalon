package ua.kharkiv.syvolotskyi.entity;

import java.util.Objects;

public class Entity {
    private Long id;
    private String name;

    public Entity() {

    }

    public Entity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity that = (Entity) o;
        return Objects.equals(id, that.id) && id != null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
