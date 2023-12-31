package pkt;
import java.util.Queue;

class RoundRobinScheduler {
    Queue<Process> realTimeQueue;
    Queue<Process> userQueue;

    public RoundRobinScheduler() {
        realTimeQueue = new ArrayDeque<>();
        userQueue = new ArrayDeque<>();
    }

    public void enqueueProcess(Process process) {
        if (process.priority == 0) {
            realTimeQueue.add(process);
        } else {
            userQueue.add(process);
        }
    }

    public void runScheduler(int timeQuantum) {
        while (!realTimeQueue.isEmpty() || !userQueue.isEmpty()) {
            if (!realTimeQueue.isEmpty()) {
                runRealTimeProcesses();
            } else {
                runUserProcesses(timeQuantum);
            }
        }
    }

    private void runRealTimeProcesses() {
        while (!realTimeQueue.isEmpty()) {
            Process process = realTimeQueue.poll();
            runProcess(process);
        }
    }

    private void runUserProcesses(int timeQuantum) {
        while (!userQueue.isEmpty()) {
            Process process = userQueue.poll();
            int remainingTime = process.processTime;

            while (remainingTime > 0) {
                int timeToRun = Math.min(remainingTime, timeQuantum);
                runProcess(process, timeToRun);
                remainingTime -= timeToRun;

                if (!realTimeQueue.isEmpty()) {
                    // Önleyici: Gerçek zamanlı işlemler gelirse kullanıcı işlemlerini durdurun
                    break;
                }
            }

            if (remainingTime > 0) {
                //İşlemin kalan süresi varsa onu kullanıcı kuyruğuna geri alır
                userQueue.add(process);
            }
        }
    }

    private void runProcess(Process process) {
        runProcess(process, process.processTime);
    }

    private void runProcess(Process process, int timeToRun) {
        System.out.println("Running process: Arrival Time " + process.arrivalTime +
                ", Priority " + process.priority +
                ", Process Time " + process.processTime +
                ", Memory " + process.memory +
                ", Printers " + process.printers +
                ", Scanners " + process.scanners +
                ", Modems " + process.modems +
                ", Drives " + process.drives +
                ", Time to Run " + timeToRun);

        // İşlem yürütmeyi burada simüle eder...

        // Belirtilen süre boyunca çalıştırıldıktan sonra işlemin tamamlandığını varsayalım
        System.out.println("Process completed.");

// Kaynakların serbest bırakılmasını ve belleğin serbest bırakılmasını simüle eder...
}
}


