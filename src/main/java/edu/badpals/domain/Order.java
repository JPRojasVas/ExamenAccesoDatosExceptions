package edu.badpals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "t_orders")
public class Order extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private long id = 0L;


    @ManyToOne
    @JoinColumn(name = "ord_wizard")
    private Wizard wizard = null;


    @OneToOne
    @JoinColumn(name = "ord_item")
    private MagicalItem item = null;

    public Order() {

    }
    public Order(Wizard wizard, MagicalItem item) {
        this.wizard = wizard;
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public MagicalItem getItem() {
        return item;
    }

    public void setItem(MagicalItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", wizard=" + wizard +
                ", item=" + item +
                '}';
    }
}
