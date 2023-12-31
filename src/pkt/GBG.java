package pkt;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;


public class GBG {
	 Queue<Process>[] priorityQueues;
	    int timeQuantum;

	    public GBG(int numPriorities, int timeQuantum) {
	        priorityQueues = new LinkedList[numPriorities];
	        for (int i = 0; i < numPriorities; i++) {
	            priorityQueues[i] = new LinkedList<>();
	        }
	        this.timeQuantum = timeQuantum;
	    }

	    public void addProcess(Process process) {
	        priorityQueues[process.priority].add(process);
	    }

	    public void runGBG() {
	        int currentTime = 0;
	        while (!allQueuesEmpty()) {
	            for (int i = 0; i < priorityQueues.length; i++) {
	                Queue<Process> currentQueue = priorityQueues[i];
	                while (!currentQueue.isEmpty()) {
	                    Process currentProcess = currentQueue.poll();
	                    executeProcess(currentProcess);

	                    // Move the process to the end of the queue if it is not completed
	                    if (currentProcess.executionTime > 0) {
	                        currentQueue.add(currentProcess);
	                    }
	                }
	            }
	            currentTime += timeQuantum;
	        }
	    }

	    private boolean allQueuesEmpty() {
	        for (Queue<Process> queue : priorityQueues) {
	            if (!queue.isEmpty()) {
	                return false;
	            }
	        }
	        return true;
	    }
}
