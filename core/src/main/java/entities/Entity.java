package entities;

import java.util.UUID;

public abstract class Entity {
    protected UUID id;

        public Entity(UUID id){
            this.id = id;
        }
    public UUID getId() {
        return id;
    }
}
