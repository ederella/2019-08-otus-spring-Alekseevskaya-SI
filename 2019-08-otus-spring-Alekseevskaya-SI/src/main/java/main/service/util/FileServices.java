package main.service.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;


@Service
public class FileServices {

	public Object save(Object p) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("predictions.json", true));
	    writer.write(p.toString()+"\n");
	     
	    writer.close();
		return p;
	}
}
