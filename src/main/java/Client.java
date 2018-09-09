
interface Charger {
    void plugIn();
}

interface EuropeanCharger {
    void plugEuro();
}

class ChargerAdapter implements Charger {
    private EuropeanCharger ukCharger;

    ChargerAdapter(EuropeanCharger charger) {
        this.ukCharger = charger;
    }

    public void plugIn() {
        System.out.print("Adapting UK plug using " + this + "\n");
        ukCharger.plugEuro();
    }
}

class Client {
    public static void main(String args[]) {
        String us = "US", uk = "UK", using = "Client using %s charger in %s... \n";
        Charger usCharger = () -> message(us, us);
        EuropeanCharger ukCharger = () -> message(uk, uk);
        Charger ukChargerAdapter = new ChargerAdapter(() -> message(uk, us));

        System.out.printf(using, us, us);
        usCharger.plugIn();

        // then they buy a UK Charger
        System.out.printf(using, uk, uk);
        ukCharger.plugEuro();
        //ukCharger.plugIn() wont work!!! Need to adapt

        //Add an adapter to "adapt" the UK charger
        System.out.printf(using, uk, us);
        ukChargerAdapter.plugIn();
        // success!!
    }

    static void message(String type, String loc) {
        System.out.printf("Successfully plugged in %s charger in %s\n\n", type, loc);
    }
}
