package watchservice;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Main {
    public static void main(String[] args) throws Exception {
        checkWatchService();
    }

    private static void checkWatchService() throws Exception {
        Path pt = Paths.get(System.getenv("HOME"));
        System.out.println("Watching " + pt);

        WatchService ws = FileSystems.getDefault().newWatchService();
        pt.register(ws, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        WatchKey key = ws.take();

        boolean poll = true;
        while (poll) {
            for (WatchEvent<?> evt : key.pollEvents()) {
                System.out.println("Event: " + evt.kind() + " on " + evt.context());
            }
            poll = key.reset();
        }
    }
}
