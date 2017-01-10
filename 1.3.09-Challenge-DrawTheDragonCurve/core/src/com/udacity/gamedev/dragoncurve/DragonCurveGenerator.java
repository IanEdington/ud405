package com.udacity.gamedev.dragoncurve;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class DragonCurveGenerator {

    enum Direction {
        LEFT,
        RIGHT;

        public static Vector2 turn(Vector2 heading, Direction turn){
            Vector2 newHeading = new Vector2();
            switch (turn){
                case LEFT:
                    newHeading.x = -heading.y;
                    newHeading.y = heading.x;
                    break;
                case RIGHT:
                    newHeading.x = heading.y;
                    newHeading.y = -heading.x;
            }
            return newHeading;
        }
    }

    static LinkedList<Direction> dragonTurns(int recursions){
        LinkedList<Direction> turns = new LinkedList<Direction>();
        turns.add(Direction.LEFT);

        for (int i = 0; i < recursions; i++){
            // Create a reversed copy of turns
            LinkedList<Direction> newTurns = new LinkedList<Direction>(turns);


            // Add a left turn to turns
            turns.add(Direction.LEFT);


            // Add reflected version of reversed to turns
            java.util.Iterator<Direction> reverseNewTurns = newTurns.descendingIterator();
            while (reverseNewTurns.hasNext()) {
                Direction turn = reverseNewTurns.next();
                switch (turn){
                    case LEFT:
                        turns.add(Direction.RIGHT);
                        break;
                    case RIGHT:
                        turns.add(Direction.LEFT);
                        break;
                }
            }

        }
        return turns;
    }

    static float[] generateDragonCurve(int width, int height, int recursions){

        LinkedList<Direction> turns = DragonCurveGenerator.dragonTurns(recursions);

        Vector2 head = new Vector2(width/2, height/2);
        Vector2 heading = new Vector2(5, 0);

        float[] curve = new float[(turns.size() + 1) * 2];

        int i = 0;
        curve[i++] = head.x;
        curve[i++] = head.y;

        //Convert the list of turns into the actual path
        for (Direction turn: turns) {
            head.add(heading);
            curve[i++] = head.x;
            curve[i++] = head.y;
            heading = Direction.turn(heading, turn);
        }


        return curve;

    }


}
