package dk.cphbusiness.dat2024.brickfactory.factory;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class BrickConstructor {
    private JavaCSG csg;
    Geometry3D brick;
    boolean[][] brickInstructions;
    protected Geometry3D mesh;
    private static int resolution = 32;
    private static int studSize = 3;
    private static int studHeight = 1;
    private static int studPlatformSize = 5;
    private static int wallThickness = 1;
    private static int brickheight = 5;
    private final double unit;


    public BrickConstructor(JavaCSG csg,double unit){
        this.csg = csg;
        //shape code here (this is stupid :))))
        this.unit = unit;

    }

    public static int getBrickheight() {
        return brickheight;
    }

    public static void setBrickheight(int brickheight) {
        BrickConstructor.brickheight = brickheight;
    }

    public Geometry3D genBrick(){
        //ensures it is empty
        Geometry3D mesh = csg.box3D(0,0,0,true);
        //initialization of storage variable
        Geometry3D tempMesh = csg.box3D(0,0,0,true);;
        for (int i = 0; i < brickInstructions.length; i++){
            for (int j = 0; j < brickInstructions[i].length; j++){
                if (brickInstructions[i][j]){
                    //places studs at location if true
                    tempMesh = stud();
                    tempMesh = csg.translate3D(i*studPlatformSize,j*studPlatformSize,0).transform(tempMesh);
                    mesh = csg.union3D(mesh,tempMesh);

                    //wall generation
                    for(int k = 0; k < 4; k++){
                        //inner corner generation (this is done with no care)
                        tempMesh = corner(90*k);
                        tempMesh = csg.translate3D(i*studPlatformSize,j*studPlatformSize,0).transform(tempMesh);
                        mesh = csg.union3D(mesh,tempMesh);
                        //wall main part
                        if(wallchecker(i,j,k)){
                            tempMesh = wall(90*k);
                            tempMesh = csg.translate3D(i*studPlatformSize,j*studPlatformSize,0).transform(tempMesh);
                            mesh = csg.union3D(mesh,tempMesh);
                        }
                    }

                }
            }
        }
        mesh = csg.scale3D(unit/studPlatformSize,unit/studPlatformSize,unit/studPlatformSize).transform(mesh);
        return mesh;
    }

    //the stud element is both the top of the brick along with the cylinder element
    public Geometry3D stud(){
        Geometry3D mesh = csg.box3D(studPlatformSize,studPlatformSize,wallThickness,false);;
        mesh = csg.union3D(mesh,csg.cylinder3D(studSize,studHeight+wallThickness,resolution,false));
        mesh = csg.translate3D(0,0,brickheight-1).transform(mesh);
        return mesh;
    }

    //the walls
    public Geometry3D wall(int angle){
        Geometry3D mesh = csg.box3D(studPlatformSize,wallThickness,brickheight,false);
        mesh = csg.translate3D(0,studPlatformSize/2,0).transform(mesh);
        mesh = csg.rotate3D(csg.degrees(0),csg.degrees(0),csg.degrees(angle)).transform(mesh);
        return mesh;
    }
    public Geometry3D corner(int angle){
        Geometry3D mesh = csg.box3D(wallThickness,wallThickness,brickheight,false);
        mesh = csg.translate3D(studPlatformSize/2,studPlatformSize/2,0).transform(mesh);
        mesh = csg.rotate3D(csg.degrees(0),csg.degrees(0),csg.degrees(angle)).transform(mesh);
        return mesh;
    }

    //this can be optimized for size, but it is not
    public boolean wallchecker(int x, int y, int side){
        switch (side){
            case 0:
                if(y+1 > brickInstructions[0].length-1){
                    return true;
                }
                else{
                    return !brickInstructions[x][y+1];
                }
            case 1:
                if(x-1 < 0){
                    return true;
                }
                else{
                    return !brickInstructions[x-1][y];
                }
            case 2:
                if(y-1 < 0){
                    return true;
                }
                else{
                    return !brickInstructions[x][y-1];
                }
            case 3:
                if(x+1 > brickInstructions.length-1){
                    return true;
                }
                else{
                    return !brickInstructions[x+1][y];
                }

        }
        return false;
    }

    public void brickInstructionsSelect(int startX,int startY,int endX,int endY,boolean toBe){
        for(int i = startX; i < endX; i++){
            for(int j = startY; j < endY; j++){
                brickInstructions[i][j] = toBe;
            }
        }
    }
    public void newBrickInstructions(int x,int y){
        brickInstructions = new boolean[x][y];
    }


    public void view(){
        csg.view(mesh);
    }
}
