package dk.cphbusiness.dat2024.brickfactory.building;

public interface ModelFactory
{
    Model createModel(ModelDesign design);
    Model createRevampModel(ModelDesign design);
}
