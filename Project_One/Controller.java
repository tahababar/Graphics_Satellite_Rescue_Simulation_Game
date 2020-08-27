import java.awt.event.*;

/**
 * This class plays the role of controller of the simulation.
 * It creates the shuttle and space station objects as well as
 * the supporting canvas and label objects.
 * 
 * @author Computer Science Department
 * @version August 2006
 */
public class Controller
implements KeyListener
{
    private SpaceStation spaceStation;
    private FuelCell fuelCell;
    private Shuttle shuttle;
    private Canvas canvas;
    private boolean sim;             // Used to determine whether (true) or 
    //      not (false)to continue the simulation 
    private boolean speedSet;        // Used to sequence the label at the top of the simulation window
    private boolean moveShuttle;     // true to move the shuttle up or down once
    private boolean launchFuelCell;  // true to launch a fuel cell
    private boolean fuelCellInMotion;
    private boolean inOrbit;         // true if the space station is orbiting
    private Label label;             // The label at the top of the simulation window

    /**
     * Create Label, SpaceStation and Shuttle objects for the canvas
     */
    public Controller()
    {
        canvas = new Canvas("Space Station Fueling Simulator",800,600);
        canvas.setListener(this);
        spaceStation = new SpaceStation(canvas);
        shuttle = new Shuttle(canvas,spaceStation);
    }

    /**
     * Creates the simulation environment and runs the simulation
     * using an indefinite while loop that looks at the state of the simulation
     * and acts accordingly each time through the loop.
     */
    public void simulate()
    {
        speedSet = false;           // speed not set yet
        label = new Label("Choose speed: B, I, or A", 350, 15,canvas);
        label.makeVisible();
        canvas.setVisible(true);

        moveShuttle = false;        // don't move the shuttle until the 'J' or 'K'  key is pressed
        launchFuelCell = false;    
        fuelCellInMotion = false;

        sim = true;
        while (sim)
        {   if (inOrbit)
            {
                spaceStation.moveSmallDistance(); 
            }
            if (moveShuttle)
            {
                shuttle.moveSmallDistance();
                moveShuttle = false;
            }
            if (launchFuelCell && shuttle.getNumberFuelCells()>0)
            {
                fuelCell = shuttle.launchCell();
                launchFuelCell = false;
                fuelCellInMotion = true;
            }          
            if (fuelCellInMotion) // The cell has been launched
            {
                fuelCell.moveSmallDistance();
                if (fuelCell.status().equals("refueled"))
                {
                    shuttle.dock();
                    canvas.wait(2000);
                    sim = false;
                }
                if (!fuelCell.status().equals("moving"))
                {
                    fuelCellInMotion = false;
                }
            }      
            if (spaceStation.getYPosition()>=canvas.getHeight())
            {
                canvas.wait(2000);
                sim = false;
            }
            canvas.wait(20);
        }   
        canvas.setVisible(false);        
    }

    /**
     * Examines the keyboard character that was just pressed and reacts 
     * accordingly, moving up or down, launching a fuel cell, setting the  
     * motion to one of three speeds or stopping the simulation
     */
    public void keyTyped(KeyEvent ke)
    {
        char myChar = Character.toUpperCase(ke.getKeyChar());
        if (myChar == 'K')
        {
            moveShuttle = true;
            shuttle.setDirection("up");
        }    
        else if (myChar == 'J')
        {
            moveShuttle = true;
            shuttle.setDirection("down"); 
        }
        else if (myChar == ' ' && !fuelCellInMotion)
        {   
            launchFuelCell = true;
        }    
        else if (myChar == 'S' && speedSet)
        {
            label.setText("Up: K, Down: J, Launch: Space");
            inOrbit = true;
        }    
        else if (myChar == 'B' || myChar == 'I' || myChar == 'A')
        {
            spaceStation.setSpeed(myChar);
            speedSet = true;
            label.setText("To start: S, To Stop: X");
        }    
        else if (myChar == 'X')
        {
            sim = false;
        }    
    }

    /**
     * Ignore methods below this point
     */
    public void keyReleased(KeyEvent ke)
    {
    }

    public void keyPressed(KeyEvent ke)
    {
    }
}  