package models.wall;

public interface wall {

    /**
     * Get wither the cell is breakable or not  .
     *
     * @return makeBreakable if true then the cell is breakable , else it is unbreakable .
     */
    boolean getCellState();

    /**
     * make the cell either breakable or not .
     *
     * @param makeBreakable if true then make the cell breakable , else make it unbreakable .
     */
    void setCellState(boolean makeBreakable);

    /**
     * Break the cell if breakable and put a bomb or empty space .
     */
    void breakCell();


}
