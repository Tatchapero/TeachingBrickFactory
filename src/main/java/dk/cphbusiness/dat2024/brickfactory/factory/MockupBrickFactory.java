package dk.cphbusiness.dat2024.brickfactory.factory;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class MockupBrickFactory implements BrickFactory
{
    private final JavaCSG csg;
    private final double unit;

    public MockupBrickFactory(JavaCSG csg, double unit)
    {
        this.csg = csg;
        this.unit = unit;
    }

    @Override
    public Brick createBasicBrick(int xSize, int ySize, int zSize)
    {
        Geometry3D res = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        res = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(res);
        return new BrickImpl(res);
    }

    @Override
    public Brick createLBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D result = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        Geometry3D diffBasicBrick = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        diffBasicBrick = csg.translate3D(-1*unit*width, -1*unit*width, 0).transform(diffBasicBrick);
        result = csg.difference3D(result, diffBasicBrick);
        result = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(result);

        return new BrickImpl(result);
    }

    @Override
    public Brick createUBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D res = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        Geometry3D diffBrick = csg.box3D((xSize*unit)-(width*unit*2),(ySize*unit),zSize*unit,false);
        res = csg.difference3D(res, csg.translate3D(0,width*unit,0).transform(diffBrick) );
        res = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(res);
        return new BrickImpl(res);
    }

    @Override
    public Brick createOBrick(int xSize, int ySize, int width, int zSize)
    {
        Geometry3D res = csg.box3D(xSize*unit, ySize*unit, zSize*unit, false);
        Geometry3D diffBrick = csg.box3D((xSize*unit)-(width*unit*2),(ySize*unit)-(width*unit*2),zSize*unit,false);
        res = csg.difference3D(res, diffBrick);
        res = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(res);
        return new BrickImpl(res);
    }

    @Override
    public Brick createHBrick(int xSize, int ySize, int width, int midSectionOffset, int zSize)
    {
        Geometry3D leftVertical = csg.box3D(xSize*unit, width*unit, zSize*unit, false);
        Geometry3D rightVertical = csg.box3D(xSize*unit, width*unit, zSize*unit, false);
        rightVertical = csg.translate3D(0, ySize*unit, 0).transform(rightVertical);
        Geometry3D midSection = csg.box3D(width*unit, ySize*unit, zSize*unit, false);
        midSection = csg.translate3D((xSize*0.5)*unit-midSectionOffset*unit, (ySize*0.5)*unit, 0).transform(midSection);
        Geometry3D result = csg.union3D(leftVertical, rightVertical);
        result = csg.union3D(result, midSection);

        result = csg.translate3D(0.5*unit*xSize, 0.5*unit*ySize, 0).transform(result);
        return new BrickImpl(result);
    }
}
