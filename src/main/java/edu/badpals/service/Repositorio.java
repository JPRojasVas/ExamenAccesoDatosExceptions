package edu.badpals.service;

import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Order;
import edu.badpals.domain.Wizard;
import edu.badpals.domain.WizardPerson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class Repositorio {

    public Optional<Wizard> loadWizard(String name){

        Optional<Wizard> wizard = Wizard.findByIdOptional(name);

        return wizard;

    }

    public Optional<MagicalItem> loadItem(String name){

        Optional<MagicalItem> item = MagicalItem.find("name = ?1", name).firstResultOptional();

        return item;

    }

    public Optional<MagicalItem> loadItem(MagicalItem item){

        Optional<MagicalItem> itemToLoad = MagicalItem.find("name = ?1 and quality = ?2 and type = ?3", item.getName(), item.getQuality(), item.getType()).firstResultOptional();

        return itemToLoad;

    }

    public List<MagicalItem> loadItems(String name){

        List<MagicalItem> listaItems = MagicalItem.listAll();

        List<MagicalItem> itemsFiltrados = new ArrayList<>();

        for (MagicalItem item : listaItems){

            if (item.getName().equals(name)){

                itemsFiltrados.add(item);

            }
        }
        return itemsFiltrados;

    }

    public void checkMudblood (Boolean comprobante){

        if (Boolean.TRUE.equals(comprobante)){
            throw new IllegalArgumentException("El mago no puede ser tipo MUDBLOOD");
        }
    }

    public void checkExists (Boolean comprobante){

        if (Boolean.FALSE.equals(comprobante)){
            throw new IllegalArgumentException("El Mago y el Item deben Existir");
        }
    }


    @Transactional
    public Optional<Order> placeOrder(String wizard, String item){

        Optional<Wizard> wizardBuying = Wizard.findByIdOptional(wizard);

        Optional<MagicalItem> itemToBuy = MagicalItem.find("name = ?1", item).firstResultOptional();

        try{

            checkMudblood(wizardBuying.get().getWizard().equals(WizardPerson.MUDBLOOD));

        }catch (IllegalArgumentException exception){
            System.out.println(exception);
            return Optional.empty();

        }

        try {

            checkExists(wizardBuying.isPresent() && itemToBuy.isPresent());

        } catch (IllegalArgumentException exception){
            System.out.println(exception);
            return Optional.empty();

        }

        Order newOrder = new Order(wizardBuying.get(), itemToBuy.get());

        newOrder.persist();
        return Optional.ofNullable(newOrder);

    }


    @Transactional
    public void createItem(String name, int quality, String type){

        MagicalItem item = new MagicalItem(name, quality, type);

        item.persist();

    }

    @Transactional
    public void createItems(List<MagicalItem> listItems){

        for (MagicalItem item : listItems){

            item.persist();

        }

    }

    @Transactional
    public void deleteItem(MagicalItem item){

        Optional<MagicalItem> itemToDelete = MagicalItem.find("name = ?1 and quality = ?2 and type = ?3", item.getName(), item.getQuality(), item.getType()).firstResultOptional();

        if (itemToDelete.isPresent()){

            itemToDelete.get().delete();

        }


    }


}



