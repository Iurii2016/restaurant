package javaonline.dao;

import javaonline.dao.entity.Position;

import java.util.List;

public interface IPositionDao {

    void addPosition(Position position);

    void deletePosition(String name);

    List<Position> getAllPosition();

    Position getPositionByName(String name);
}
