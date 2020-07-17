package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Random;

public class Main {
    private static byte[] startField;

    private static byte[] terminateField;

    private static int stepCount = 70; //количество случайных предвежений фишок при генерации поля

    private static int sideSize = 3; // размер поля

    private static boolean isReadFromStream = true;

    private static boolean isShowStatistic = false;


    public static void main(String[] args) {
        int size = sideSize * sideSize;
        terminateField = getTerminalState(sideSize, size);
        FifteenPrice rules = new FifteenPrice2(sideSize, terminateField);
        FifteenState startState = new FifteenState(null, sideSize);
        startField = generateStartState(rules, stepCount);
        startState.setField(startField);
        Astar<FifteenState, FifteenPrice> astar = new Astar<FifteenState, FifteenPrice>(
                rules);
        long time = System.currentTimeMillis();
        Collection<State> res = astar.search(startState);
        time = System.currentTimeMillis() - time;

        if (res == null) {
            System.out.println("Решение не найдено");
            return;
        } else {
            for (State s : res) {
                System.out.println(s.toString());
            }
        }

        System.out.println("Time: " + time + "ms");

        System.out.println("Количество ходов: " + (res.size() - 1));




    }

    private static byte[] readStartState() throws IOException {
        System.out.println("Reading state from input stream...");
        InputStreamReader istr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(istr);
        String line = null;
        sideSize = 0;
        StringBuffer buf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            buf.append(line + "\n");
            sideSize++;
        }
        String state = buf.toString();
        if (state.isEmpty()) {
            return null;
        } else {
            return FifteenState.parseField(state);
        }
    }

    /**
     * Генерирует начальное состояние путем swap начальных перестановок.

     */
    private static byte[] generateStartState(FifteenPrice rules, int swapCount) {
        int stepCount = swapCount;
        byte[] startState = rules.getTerminateState();

        int[] actions = rules.getActions();
        Random r = new Random();
        while (stepCount > 0) {
            int j = r.nextInt(actions.length);
            byte[] state = rules.doAction(startState, actions[j]);
            if (state != null) {
                startState = state;
                stepCount--;
            }
        }
        return startState;
    }


    private static byte[] getTerminalState(int sideSize, int size) {
        if (terminateField == null) {
            terminateField = new byte[size];
            byte k = 0;
            for (int i = 0; i < sideSize; i++) {
                for (int j = 0; j < sideSize; j++) {
                    terminateField[j + i * sideSize] = ++k;
                }
            }
            terminateField[size - 1] = 0;
        }
        return terminateField;
    }


}
