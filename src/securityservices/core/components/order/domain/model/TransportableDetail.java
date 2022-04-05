package securityservices.core.components.order.domain.model;

import securityservices.core.components.shared.products.Storable;

public class TransportableDetail {

    protected int amount;
    protected Storable product;

    public TransportableDetail() {
    }

    public TransportableDetail(Storable prod, int amount) {
        this.product = prod;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String getCode(){
        return this.product.getCode();
    }
        
    public double getWeight(){
        return this.product.getWeight()*amount;
    }
    
    public double getVolum(){
        return this.product.getVolum()*amount;
    }

}
