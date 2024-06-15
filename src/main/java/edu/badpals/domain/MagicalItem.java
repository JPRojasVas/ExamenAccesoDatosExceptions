package edu.badpals.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "t_items")
public class MagicalItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id = 0L;


    @Column(name = "item_name")
    private String name = "";

    @Column(name = "item_quality")
    private int quality = 0;

    @Column(name = "item_type")
    private String type = "";

    private MagicalItem(){

    }

    public MagicalItem(String name, int quality, String type) {
        this.name = name;
        this.quality = quality;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MagicalItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quality=" + quality +
                ", type='" + type + '\'' +
                '}';
    }
}
