package gamestore.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "orders")
public class Order extends BaseEntity {

    private User buyer;
    private Set<Game> products;

    public Order() {
    }

    @ManyToOne()
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
}
