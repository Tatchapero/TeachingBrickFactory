package dk.cphbusiness.dat2024.brickfactory.factory;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class BrickFactoryMockup implements BrickFactory
{
    private final JavaCSG csg;
    private final double unit;
    private BrickConstructor brickConstructor;

    public BrickFactoryMockup(JavaCSG csg, double unit)
    {
        this.csg = csg;
        this.unit = unit;
        brickConstructor = new BrickConstructor(csg,unit);
    }

    @Override
    public Brick createBasicBrick(int xSize, int ySize, int zSize)
    {
        brickConstructor.newBrickInstructions(xSize,ySize);
        brickConstructor.setBrickheight(zSize);
        brickConstructor.brickInstructionsSelect(0,0,xSize,ySize,true);
        Geometry3D res = brickConstructor.genBrick();
        return new BrickImpl(res);
    }

    @Override
    public Brick createLBrick(int xSize, int ySize, int width, int zSize)
    {
        brickConstructor.newBrickInstructions(xSize,ySize);
        brickConstructor.setBrickheight(zSize);
        brickConstructor.brickInstructionsSelect(0,0,xSize,ySize,true);
        brickConstructor.brickInstructionsSelect(width,0,xSize,ySize-width,false);
        Geometry3D res = brickConstructor.genBrick();
        return new BrickImpl(res);
    }

    @Override
    public Brick createUBrick(int xSize, int ySize, int width, int zSize)
    {
        brickConstructor.newBrickInstructions(xSize,ySize);
        brickConstructor.setBrickheight(zSize);
        brickConstructor.brickInstructionsSelect(0,0,xSize,ySize,true);
        brickConstructor.brickInstructionsSelect(width,0,xSize-width,ySize-width,false);
        Geometry3D res = brickConstructor.genBrick();
        return new BrickImpl(res);
    }

    @Override
    public Brick createOBrick(int xSize, int ySize, int width, int zSize)
    {
        brickConstructor.newBrickInstructions(xSize,ySize);
        brickConstructor.setBrickheight(zSize);
        brickConstructor.brickInstructionsSelect(0,0,xSize,ySize,true);
        brickConstructor.brickInstructionsSelect(width,width,xSize-width,ySize-width,false);
        Geometry3D res = brickConstructor.genBrick();
        return new BrickImpl(res);
    }

    @Override
    public Brick createHBrick(int xSize, int ySize, int width, int midSectionOffset, int zSize)
    {
        brickConstructor.newBrickInstructions(xSize,ySize);
        brickConstructor.setBrickheight(zSize);
        brickConstructor.brickInstructionsSelect(0,0,width,ySize,true);
        brickConstructor.brickInstructionsSelect(xSize-width,0,xSize,ySize,true);
        if (width+midSectionOffset<ySize){
            brickConstructor.brickInstructionsSelect(0,midSectionOffset,xSize,midSectionOffset+width,true);
        }


        Geometry3D res = brickConstructor.genBrick();
        return new BrickImpl(res);
    }
}
