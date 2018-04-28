package Application;

import java.util.Scanner;

import Application.Client.View.Eventhandler.EventHandler;

public class ClientMain {
	public static void main(String[] args) {Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ip of the robot");
		String ip = scanner.nextLine();
		EventHandler ev = new EventHandler(ip);
	}
}
