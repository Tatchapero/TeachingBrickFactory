package dk.cphbusiness.dat2024.brickfactory.building;

import dk.cphbusiness.dat2024.brickfactory.Brick;
import dk.cphbusiness.dat2024.brickfactory.BrickFactory;

public class ModelTester implements ModelDesign {

    @Override
    public Model designModel(ModelBuildTool buildTool, BrickFactory brickFactory)
    {
        Brick basicBrick = brickFactory.createBasicBrick(2, 2, 1);
        Brick lBrick = brickFactory.createLBrick(8,8, 1, 1);
        Brick uBrick = brickFactory.createUBrick(8, 8, 1, 1);
        Brick oBrick = brickFactory.createOBrick(8, 8, 1, 1);
        Brick hBrick = brickFactory.createHBrick(4, 4, 1, 2, 1);
        basicBrick = buildTool.translate(0, 0, 0, basicBrick);
        lBrick = buildTool.translate(5, 5, 0, lBrick);
        uBrick = buildTool.translate(-5, 5, 0, uBrick);
        oBrick = buildTool.translate(5, -5, 0, oBrick);
        hBrick = buildTool.translate(-5, -5, 0, hBrick);
        buildTool.placeBrick(basicBrick);
        buildTool.placeBrick(lBrick);
        buildTool.placeBrick(uBrick);
        buildTool.placeBrick(oBrick);
        buildTool.placeBrick(hBrick);

        return buildTool.getModel();
    }
}
