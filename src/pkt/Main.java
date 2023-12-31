package pkt;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;


public class Main {

	class Process implements Comparable<Process> {
	    int arrivalTime;
	    int priority;
	    int executionTime;
	    int memoryRequirement;
	    int printers;
	    int scanners;
	    int modems;
	    int cdDrives;

	    public Process(int arrivalTime, int priority, int executionTime, int memoryRequirement, int printers, int scanners, int modems, int cdDrives) {
	        this.arrivalTime = arrivalTime;
	        this.priority = priority;
	        this.executionTime = executionTime;
	        this.memoryRequirement = memoryRequirement;
	        this.printers = printers;
	        this.scanners = scanners;
	        this.modems = modems;
	        this.cdDrives = cdDrives;
	    }

	    @Override
	    public int compareTo(Process other) {
	        // Comparing based on priority for the PriorityQueue
	        return Integer.compare(this.priority, other.priority);
	    }
	}

	class Dispatcher {
	    Queue<Process> realTimeQueue;
	    Queue<Process> userQueue;

	    public Dispatcher() {
	        this.realTimeQueue = new PriorityQueue<>();
	        this.userQueue = new PriorityQueue<>();
	    }

	    public void addProcess(Process process) {
	        if (process.priority == 0) {
	            realTimeQueue.add(process);
	        } else {
	            userQueue.add(process);
	        }
	    }

	    public void run() {
	        while (!realTimeQueue.isEmpty()) {
	            Process process = realTimeQueue.poll();
	            executeProcess(process);
	        }

	        while (!userQueue.isEmpty()) {
	            Process process = userQueue.poll();
	            executeProcess(process);
	        }
	    }

	    private void executeProcess(Process process) {
	        // Simulate process execution
	        System.out.println("Executing Process: " + process.priority + " - " + process.executionTime + "s");
	        // Add logic for handling resources, memory, etc.

	        // Simulate process completion
	        System.out.println("Process Completed: " + process.priority);
	    }
	}

	public class Main {
	    public static void main(String[] args) {
	    	  Dispatcher dispatcher = new Dispatcher();
	    	  BellekTahsisi bellek = new BellekTahsisi(2, 2, 1, 1);

	          try (BufferedReader reader = new BufferedReader(new FileReader("giris.txt"))) {
	              String line;

	              while ((line = reader.readLine()) != null) {
	                  // Her bir satırdan gelen veriyi işleyerek bir Process nesnesi oluşturun
	                  Process process = parseProcess(line);

	                  // Dispatcher sınıfına ekleyin
	                  dispatcher.addProcess(process);
	              }

	              // Dispatcher'ı çalıştırın
	              dispatcher.run();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	    }
	    // Satırdaki veriyi okuyarak bir Process nesnesi oluşturan yardımcı bir metod
	    private static Process parseProcess(String line) {
	        String[] parts = line.split(",");
	        int arrivalTime = Integer.parseInt(parts[0].trim());
	        int priority = Integer.parseInt(parts[1].trim());
	        int executionTime = Integer.parseInt(parts[2].trim());
	        int memoryRequirement = Integer.parseInt(parts[3].trim());
	        int printers = Integer.parseInt(parts[4].trim());
	        int scanners = Integer.parseInt(parts[5].trim());
	        int modems = Integer.parseInt(parts[6].trim());
	        int cdDrives = Integer.parseInt(parts[7].trim());

	        return new Process(arrivalTime, priority, executionTime, memoryRequirement, printers, scanners, modems, cdDrives);
	    }
	}

}
