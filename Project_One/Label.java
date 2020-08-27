/**
 * A class to handle the graphing of text
 * 
 * @author Computer Science Department
 * @version August 2005
 */

public class Label
{
    // instance variables - replace the example below with your own
    private String label;
    private int xCoord;
    private int yCoord;
    private String color;
    private boolean isVisible;
    private Canvas canvas;

    /**
     * Constructor for objects of class Label.  Place "invisible, blank" label at upper lefthand corner
     * of the canvas in black font.
     */
    public Label(Canvas canvas)
    {
        // initialise instance variables
        label = "";
        xCoord = 0;
        yCoord = 0;
        color = "black";
        isVisible = false;
        this.canvas = canvas;
    }

    /**
     * Constructor for objects of class Label.  Place "invisible" label specified by the first
     * parameter at the position specified by the second and third parameters of the canvas.
     */
    public Label(String label, int xCoord, int yCoord, Canvas canvas)
    {
        // initialise instance variables
        this.label = label;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        color = "black";
        isVisible = false;
        this.canvas = canvas;
    }
    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this circle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Move the label to the location defined by xDistance and yDistance
     * @param  distanceX -- how far to move (right or left) in the horizontal direction
     * @param  distanceY -- how far to move (up or down) in the vertical direction
     */
    public void moveTo(int newXCoord, int newYCoord)
    {
       erase();
       yCoord = newYCoord;
       xCoord = newXCoord;
       draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     * 
     * @param newColor 
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }
    /**
     * Draw the label with current specifications (color, message and coordinates)
     * on the canvas.
     */
    private void draw()
    {
        if(isVisible) {
            canvas.drawString(this, color, label, xCoord, yCoord);
        }
    }

    /**
     * Remove the label from the canvas
     */
    public void erase()
    {
        if(isVisible) {
            canvas.erase(this);
        }
    }

    /**
     * Change the text of this label.
     */
    public void setText(String label)
    {
        this.label = label;
        draw();
    }
    
    /**
     * @returns xCoord -- the x coordinate of the label anchor.
     */
    public int getXCoord()
    {
        return xCoord;
    }
    
    /**
     * @returns yCoord -- the y coordinate of the label anchor.
     */
    public int getYCoord()
    {
        return yCoord;
    }
}
