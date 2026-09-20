package lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    //override
    public int calculateCharge() {
        int pages = getPages();
        int cost = 0;
        if (pages <= 10) {
            cost = pages * 1500;
            } else {
                cost = (10 * 1500) + ((pages - 10) * 1000);
            }
            cost += 2000;
            return cost;
        } 

        //override
        public String label() {
            return "Colour";
        }
}
