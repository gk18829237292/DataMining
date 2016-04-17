package com.gk.dmMethod.KNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import com.sun.xml.internal.fastinfoset.Encoder;


/**
 * 加载的为txt格式
 * 默认属性为 枚举型的
 * @author pc_home
 *
 */
public class KNN {
	
	String trainSet = "";
	String testSet = "";
	
	boolean isTrainSet = false;
	boolean isTestSet = false;
	
	public int k = 3;
	
	public void setTrainSet(String trainSet){
		this.trainSet = trainSet;
		isTrainSet = true;
	}
	
	public void setTestSet(String testSet){
		this.testSet  = testSet;
		isTestSet = true;
	}
	
	
	public void precdite(OutputStream out) throws Exception{
		if(!isTrainSet) throw new Exception("1");
		if(!isTestSet) throw new Exception("2");
		Queue<NameValueEntry> queue = new PriorityQueue<NameValueEntry>();
		BufferedReader reader = new BufferedReader(new FileReader(testSet));
		String line ="";
		BufferedReader reader1 = new BufferedReader(new FileReader(trainSet));
		List<List<String>> list = new ArrayList<List<String>>();
		
		while((line = reader1.readLine()) != null){
			list.add(Arrays.asList(line.split(" ")));
		}
		reader1.close();
		String[] tokens;
		while((line = reader.readLine()) != null){
			tokens = line.split(" ");
			queue.clear();
			for(List<String> list_line:list){
				double distance = 0;
				for(int i =0;i<tokens.length - 1;i++){
					if(tokens[i].equals(list_line.get(i))) distance ++;
				}
				queue.add(new NameValueEntry(distance,tokens[tokens.length-1]));
			}
			Map<String,Integer> map = new HashMap<String,Integer>();
			NameValueEntry nv=null;
			for(int i =0;i<k && !queue.isEmpty();i++){
				nv = queue.poll();
				if(!map.containsKey(nv.name)) map.put(nv.name,1);
				else map.put(nv.name,map.get(nv.name) + 1);
			}
			int max =-1;
			String name = null;
			for(Map.Entry<String, Integer> entry:map.entrySet()){
				if(entry.getValue() > max){
					max = entry.getValue();
					name = entry.getKey();
				}
			}
			out.write(name.getBytes());
//			name.getBytes();
//			out.write(Encode/r.UTF_8.getBytes(name));
		}
		
		reader.close();
		
	}
	
}
