package vacuum_world.Search.World;

public class Cell {

    public boolean isClean;
    public boolean isBlocked;
    public boolean hasRobot;
    public char charVisual;

    public Cell(char cellChar, int x, int y){
        charVisual = cellChar;
        switch (cellChar){
            case CellCharacter.dirt:
                isBlocked = false;
                isClean = false;
                hasRobot = false;
                break;
            case CellCharacter.clean:
                isBlocked = false;
                isClean = true;
                hasRobot = false;
                break;
            case CellCharacter.blocked:
                isBlocked = true;
                hasRobot = false;
                break;
            case CellCharacter.robot:
                isBlocked = false;
                isClean = true;
                hasRobot = true;
                charVisual = '@';
                break;
            default:
                throw new IllegalArgumentException("Cell character not recognized");
        }
    }
}
