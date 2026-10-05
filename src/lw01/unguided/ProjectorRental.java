package lw01.unguided;

public class ProjectorRental extends Rental {
    
    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge(int days) {
        int days = getDays();
        int RentalCharge = 0;
        if (days <= 3) {
            RentalCharge = days * 60000;
        } else {
            RentalCharge = (3 * 60000) + ((days - 3) * 45000);
        }
        RentalCharge += 20000;
        return RentalCharge;
    }

    @Override 
    public String label() {
        return "Projector";
    }
} 
    

