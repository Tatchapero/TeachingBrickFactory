package dk.cphbusiness.dat2024.brickfactory.building.impl;

import dk.cphbusiness.dat2024.brickfactory.BrickFactory;
import dk.cphbusiness.dat2024.brickfactory.building.Model;
import dk.cphbusiness.dat2024.brickfactory.building.ModelBuildTool;
import dk.cphbusiness.dat2024.brickfactory.building.ModelDesign;
import dk.cphbusiness.dat2024.brickfactory.building.ModelFactory;
import dk.cphbusiness.dat2024.brickfactory.factory.BrickFactoryMockup;
import dk.cphbusiness.dat2024.brickfactory.factory.MockupBrickFactory;
import org.abstractica.javacsg.JavaCSG;

public class MockupModelFactoryImpl implements ModelFactory
{
    private final BrickFactory brickFactory;
    private final BrickFactory brickFactoryRevamp;
    private final ModelBuildTool buildTool;

    public MockupModelFactoryImpl(JavaCSG csg, double unit)
    {
        brickFactory = new MockupBrickFactory(csg, unit);
        brickFactoryRevamp = new BrickFactoryMockup(csg, unit);
        buildTool = new ModelBuildToolImpl(csg, unit);
    }

    @Override
    public Model createModel(ModelDesign design)
    {
        return design.designModel(buildTool, brickFactory);
    }

    @Override
    public Model createRevampModel(ModelDesign design)
    {
        return design.designModel(buildTool, brickFactoryRevamp);
    }
}
