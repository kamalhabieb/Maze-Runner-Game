package models.wall;

public interface wall {

    /**
     * make the cell either breakable or not .
     * @param makeBreakable if true then make the cell breakable , else make it unbreakable .
     */
    void setCellState(boolean makeBreakable);

    /**
     * Get wither the cell is breakable or not .
     *
     * @return true if the cell is breakable or false if it is unbreakable .
     */
    boolean getCellState();

    /**
     * Break the cell if breakable and put a bomb or empty space .
     */
    void breakCell();


}
