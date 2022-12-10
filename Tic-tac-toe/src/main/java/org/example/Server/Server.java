package org.example.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {
    private static ServerSocket serverSocket;
    private static BufferedReader reader;
    private static BufferedReader consoleReader;
    private static PrintWriter writer;
    private static Socket client;
    private static Character[][] tttCells = new Character[3][3];
    private static Set<String> signs = Stream.of("x", "o").collect(Collectors.toSet());
    private static int counter = 0;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1111);
            client = serverSocket.accept();
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            writer.println("Please choose your sign: x or o");

            String clientSign;
            String serverSign;
            while(!signs.contains(clientSign = reader.readLine().toLowerCase())) {
                writer.println("Input correct sign like x or o");
            }
            writer.println("Good");
            if(clientSign.equals("o")) {
                serverSign = "x";
                putServerSign(serverSign);
            }
            else {
                serverSign = "o";
            }
            while(true) {
                writer.println("Input coordinates for next move like 0 0");
                sendCells();
                putClientSign(clientSign);
                if(counter == 9) {
                    writer.println("Tie");

                }
                else if(isEnded()){
                    writer.println("You are winner");
                    System.out.println("You lost");
                    while(!client.isClosed()) {

                    }
                    break;
                }
                printCells();
                putServerSign(serverSign);
                if(counter == 9) {
                    writer.println("Tie");
                }
                else if(isEnded()){
                    writer.println("You lost");
                    System.out.println("You are winner");
                    while(!client.isClosed()) {

                    }
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sendCells() {
        for(int i = 0; i < 3; i++) {
            StringBuilder row = new StringBuilder();
            for(int j = 0; j < 3; j++) {
                Character sign = tttCells[i][j] == null ? ' ' : tttCells[i][j];
                row.append(sign);
                if(j != 2) {
                    row.append("|");
                }
            }
            writer.println(row);
            if(i != 2) {
                writer.println("- - -");
            }
        }
    }
    private static void printCells() {
        for(int i = 0; i < 3; i++) {
            StringBuilder row = new StringBuilder();
            for(int j = 0; j < 3; j++) {
                Character sign = tttCells[i][j] == null ? ' ' : tttCells[i][j];
                row.append(sign);
                if(j != 2) {
                    row.append("|");
                }
            }
            System.out.println(row);
            if(i != 2) {
                System.out.println("- - -");
            }
        }
    }

    private static void putTheSign(String sign, int x, int y) {
            tttCells[x][y] = sign.charAt(0);
    }
    private static void putClientSign(String clientSign) throws IOException {
        counter++;
        while(true) {
            writer.println("Input coordinates like 0 0");
            String[] coordinates = reader.readLine().split(" ");
            if(coordinates.length != 2) {
                continue;
            }
            try {
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if(x > 2 || x < 0 || y > 2 || y < 0) {
                    continue;
                }
                else if(tttCells[x][y] != null) {
                    continue;
                }
                putTheSign(clientSign, x, y);
            } catch (IllegalArgumentException e) {
                continue;
            }
            writer.println("Good");
            break;
        }

    }
    private static void putServerSign(String serverSign) throws IOException {
        counter++;
        while(true) {
            System.out.println("Input coordinates like 0 0");
            String[] coordinates = consoleReader.readLine().split(" ");
            if(coordinates.length != 2) {
                continue;
            }
            try {
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                if(x > 2 || x < 0 || y > 2 || y < 0) {
                    continue;
                }
                else if(tttCells[x][y] != null) {
                    continue;
                }
                putTheSign(serverSign, x, y);
            } catch (IllegalArgumentException e) {
                continue;
            }
            System.out.println("Good");
            break;
        }
    }
    private static boolean isEnded() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; true; j++) {
                if(tttCells[i][j] == null || tttCells[i][j] != tttCells[i][j + 1]) {
                    break;
                }
                if(j == 1) {
                    return true;
                }
            }
        }
        for(int j = 0; j < 3; j++) {
            for(int i = 0; true; i++) {
                if(tttCells[i][j] == null || tttCells[i][j] != tttCells[i + 1][j]) {
                    break;
                }
                if(i == 1) {
                    return true;
                }
            }
        }
        for(int i = 0; true; i++) {
            if(tttCells[i][i] == null || tttCells[i][i] != tttCells[i+1][i+1]) {
                break;
            }
            if(i == 1) {
                return true;
            }
        }
        for(int i = 0; true; i++) {
            if(tttCells[i][2 - i] == null || tttCells[i][2-i] != tttCells[i+1][2 - i - 1]) {
                break;
            }
            if(i == 1) {
                return true;
            }
        }
        return false;
    }

}

