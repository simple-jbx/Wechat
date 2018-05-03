package test;
import java.util.*;
import java.lang.Math;
import bean.*;
public class testclass {
	 public static void main(String[] args) {
	 
		for(int i = 1; i<= 1; i++) {
			System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		}
					
		
	}
}

class Employ {
		private static int nextId;
		
		private int id;
		private String name = "";
		private double salary;
		
		
		static {
			Random generator = new Random();
			nextId = generator.nextInt(1000);
		}
		
		
		{
			id = nextId;
			nextId++;
		}
		
		public Employ(String n, double s) {
			name = n;
			salary = s;
		}
		
		public Employ(double s) {
			this("Employ #" + nextId, s);
		}
		
		public Employ() {
			
		}
		
		public String getName() {
			return name;
		}
		
		public double getSalary() {
			return salary;
		}
		
		public int getId() {
			return id;
		}
}