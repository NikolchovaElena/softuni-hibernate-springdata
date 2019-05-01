package app.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Position {
    private enum PositionDescription {
        Goalkeeper,
        Defender,
    }

    private String id;
    private PositionDescription positionDescription;
    private Set<Player>positions;

    public Position() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 2)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "position_description")
    public PositionDescription getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(PositionDescription positionDescription) {
        this.positionDescription = positionDescription;
    }

    @OneToMany(mappedBy = "position",targetEntity = Player.class)
    public Set<Player> getPositions() {
        return positions;
    }

    public void setPositions(Set<Player> positions) {
        this.positions = positions;
    }
}
