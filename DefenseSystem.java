public class DefenseSystem {
    public static void main(String[] args) {
        MainController mainController = new MainController();

        Helicopter helicopter = new Helicopter(mainController);
        Tank tank = new Tank(mainController);
        Submarine submarine = new Submarine(mainController);

        mainController.addUnit(helicopter);
        mainController.addUnit(tank);
        mainController.addUnit(submarine);

        helicopter.updateStrength(Strength.HIGH);
        tank.updateStrength(Strength.STRONG);
        submarine.updateStrength(Strength.MEDIUM);
    }
}
