/**
 * The fuelcell travels toward the space station when launched.
 * It travels horizontally on a line extending from the apex of the shuttle.
 * 
 * @author Computer Science Department
 * @version September 2006
 */
public class FuelCell
{
    private Circle fuelCell;
    private SpaceStation spaceStation;    // local reference for the space station
    private Canvas canvas;                // local reference for the canvas
    private String cellStatus;            // "moving", "missed", "refueled"

    /**
     * The constructor creates a fuel cell that can reference the space station and
     * the shuttle. It needs to know about these objects so it can coordinate docking
     * operations for itself and for the shuttle.
     * 
     * @param theCanvas the canvas to use for all graphics
     * @param theSpaceStation the spaceStation -- created by the Controller in this application
     */
    public FuelCell(Canvas theCanvas, SpaceStation theSpaceStation)
    {
        canvas = theCanvas;
        spaceStation = theSpaceStation;
        fuelCell = new Circle(canvas);
        fuelCell.moveTo(canvas.getHeight()/2,canvas.getWidth()/2);
        fuelCell.makeInvisible();
        cellStatus = "moving";
    }

    /**
     * launch sets initial conditions for the launch.
     */
    public void launch(int xPosition, int yPosition)
    {
        fuelCell.moveTo(xPosition,yPosition); //move the fuel cell to launch position
        fuelCell.makeVisible(); // and make it visible
    }

    /**
     * Repeated calls to moveSmallDistance will eventually get the fuel cell close to the space station or
     * miss the space station, sending the fuel cell off the right side of the canvas.
     */
    public void moveSmallDistance()
    {
        // Check to see if the cell is "close enough" to the space station
        if ((Math.abs(spaceStation.getXPosition()-fuelCell.getXPosition())<=fuelCell.getDiameter()/2) &&
        (Math.abs(spaceStation.getYPosition()-fuelCell.getYPosition())<=fuelCell.getDiameter()/2))
        {
            fuelCell.moveTo(spaceStation.getXPosition(),spaceStation.getYPosition()); // Now dock the fuel cell
            canvas.wait(3000);
            fuelCell.makeInvisible();
            spaceStation.reFuel();
            cellStatus = "refueled";
        }
        else if ( fuelCell.getXPosition()<canvas.getWidth()) // Move the fuel cell a little more to the rignt.
        {
            fuelCell.moveDirection(10,0);
        }
        else  // The fuel cell missed
        {
            cellStatus = "missed";
            fuelCell.makeInvisible();
        }
    }    

    /**
     * status() returns the status of the cell
     * @return status "refueled", "missed", or "moving"
     */
    public String status()
    {
        return cellStatus;
    }
}