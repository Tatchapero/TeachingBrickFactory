package dk.cphbusiness.dat2024.brickfactory.building;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;

public class HelicopterPlatform implements ModelDesign

{
    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {

        Brick oBrick = brickFactory.createOBrick(7,7,1,3);
        Brick hBrick = brickFactory.createHBrick(3, 3, 1, 1, 3);
        Brick basicBrick = brickFactory.createBasicBrick(7,7,5);
        Brick leg1 = brickFactory.createBasicBrick(1,1,25);
        Brick leg2 = brickFactory.createBasicBrick(1,1,25);
        Brick leg3 = brickFactory.createBasicBrick(1,1,25);
        Brick leg4 = brickFactory.createBasicBrick(1,1,25);
        hBrick = buildTool.translate(2,2,0, hBrick);
        basicBrick = buildTool.translateZ(-1, basicBrick);
        leg1 = buildTool.translate(0,0, -5, leg1);
        leg2 = buildTool.translate(6,0, -5, leg2);
        leg3 = buildTool.translate(0,6, -5, leg3);
        leg4 = buildTool.translate(6,6, -5, leg4);
        buildTool.placeBrick(oBrick);
        buildTool.placeBrick(hBrick);
        buildTool.placeBrick(basicBrick);
        buildTool.placeBrick(leg1);
        buildTool.placeBrick(leg2);
        buildTool.placeBrick(leg3);
        buildTool.placeBrick(leg4);

        return buildTool.getModel();
    }
}