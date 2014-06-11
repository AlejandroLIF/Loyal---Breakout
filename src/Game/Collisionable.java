package Game;


import java.awt.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alejandro
 */
public interface Collisionable {
    Shape getCollisionBoundary();
    void checkCollision(Collisionable c);
}
