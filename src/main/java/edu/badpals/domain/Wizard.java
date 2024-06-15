package edu.badpals.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "t_wizards")
public class Wizard extends PanacheEntityBase {

    @Id
    @Column(name = "wizard_name")
    private String name = "";


    @Column(name = "wizard_dexterity")
    private int dexterity = 0;


    @Column(name = "wizard_person")
    @Enumerated(EnumType.STRING)
    private WizardPerson wizard = null;

    public Wizard(){

    }

    public Wizard(String name, int dexterity, WizardPerson wizard) {
        this.name = name;
        this.dexterity = dexterity;
        this.wizard = wizard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public WizardPerson getWizard() {
        return wizard;
    }

    public void setWizard(WizardPerson wizard) {
        this.wizard = wizard;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + name + '\'' +
                ", dexterity=" + dexterity +
                ", wizard=" + wizard +
                '}';
    }
}
