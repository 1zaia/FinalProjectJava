public class Winner{

    public int countPlayer;
    public int contTable;

    public boolean condition() {
        if(countPlayer < 3) return false;
        else if(contTable < 9) return false;
        else return true;
    } 
}
