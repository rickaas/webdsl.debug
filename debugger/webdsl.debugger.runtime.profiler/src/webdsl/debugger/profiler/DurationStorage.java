package webdsl.debugger.profiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DurationStorage {
	
	public static final int BUFFER_SIZE = 100;
	
	private Map<String, long[]> startPerType;
	private Map<String, long[]> stopPerType;
	private Map<String, Integer> counterPerType;
	
	private FileWriter fstream;
	private BufferedWriter out;
	
	private String location;
	
	public DurationStorage(String location) throws IOException {
		this.location = location;
		init();
	}
	
	private void init() throws IOException {
		File f = new File(this.location);
		//System.out.println(location + ": " + f.getAbsolutePath());
		
		this.fstream = new FileWriter(f);
		this.out = new BufferedWriter(fstream);
		
		this.startPerType = new HashMap<String, long[]>();
		this.stopPerType = new HashMap<String, long[]>();
		this.counterPerType = new HashMap<String, Integer>();
	}
	
	private int getCounter(String type) {
		if (!counterPerType.containsKey(type)) {
			counterPerType.put(type, 0);
		}
		return counterPerType.get(type);
	}
	
	private void setCounter(String type, int counter) {
		counterPerType.put(type, counter);
	}
	
	private long[] getStart(String type) {
		if (!startPerType.containsKey(type)) {
			startPerType.put(type, new long[BUFFER_SIZE]);
		}
		return startPerType.get(type);
	}

	private long[] getStop(String type) {
		if (!stopPerType.containsKey(type)) {
			stopPerType.put(type, new long[BUFFER_SIZE]);
		}
		return stopPerType.get(type);
	}
	
	public void start(String type) {
		getStart(type)[getCounter(type)] = System.currentTimeMillis();
	}
	
	public void stop(String type) {
		getStop(type)[getCounter(type)] = System.currentTimeMillis();
		try {
			next(type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void next(String type) throws IOException {
		int counter = getCounter(type);
		long[] array = getStart(type);
		if (counter == array.length - 1) {
			flush(type);
		} else {
			counter++;
			setCounter(type, counter);
		}
	}
	
	private void clearBuffer(String type) {
		startPerType.put(type, new long[BUFFER_SIZE]);
		stopPerType.put(type, new long[BUFFER_SIZE]);
	}

	private void flush(String type) throws IOException {
		int counter = getCounter(type);
		long[] startArray = getStart(type);
		long[] stopArray = getStop(type);
		
		for(int i = 0; i < counter; i++) {
			this.out.write(type);
			this.out.write("\t");
			this.out.write(Long.toString(startArray[i]));
			this.out.write("\t");
			this.out.write(Long.toString(stopArray[i]));
			this.out.newLine();
		}
		this.out.flush();
		counter = 0;
		setCounter(type, counter);
		clearBuffer(type);
	}
	
	public void flush() throws IOException {
		for(String type : this.counterPerType.keySet()) {
			flush(type);
		}
	}
	
	public void close() throws IOException {
		this.flush();
		this.out.flush();
		this.fstream.flush();
		this.out.close();
		this.fstream.close();
	}

}
