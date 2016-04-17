package com.gk.dmMethod;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

/**
*���ر�Ҷ˹��ҪԤ��ı����Ƿ�������
*
*
*1.����ѵ�����ļ���ָ��ҪԤ������[Ĭ�����һ��ΪԤ������]
*2.����ģ��
*3.���ò��Լ��ļ���[�ж��ļ��Ƿ����Ҫ������ȱʧ]
*4.Ԥ�⣬ָ�����[Ĭ��Console�����]
*
*
*/
public  class NaiveBayesian{
	
	private boolean isSetTrainSet;
	private boolean isModelGenerted;
	private boolean isSetTestSet;
	
	private String trainSet;
	private String testSet;
	
	private int num = -1;
	private int total_trainNum = 0; 
	Map<String, Double> mapProba = new HashMap<String,Double>();
	public NaiveBayesian(){
		isSetTestSet = false;
		isModelGenerted = false;
		isSetTestSet = false;
	}
	
	public void setTrainSetFile(String trainSetFile) throws Exception{
		setTrainSet(trainSetFile);
		isSetTrainSet = true;
		isModelGenerted = false;
	}
	
	/**
	 * ֻ�Ǹ�ֵ
	 * @param trainSet
	 * @throws Exception
	 */
	private void setTrainSet(String trainSet)throws Exception{
		this.trainSet = trainSet;
	}
	
	public void setTestSetFile(String testSetFile){
		setTestSet(testSetFile);
		isSetTestSet = true;
	}
	
	private void setTestSet(String testSet){
		this.testSet = testSet;
	}
	
	public void generatePredictedModel() throws IOException, Exception{
		//TODO generate the model
		BufferedReader reader;

			System.out.println(trainSet);
			reader = new BufferedReader(new FileReader(trainSet));
			String line ="";
			while((line= reader.readLine()) != null){
				addToMap(line);
			}
			reader.close();
			isModelGenerted = true;
		}


		
		
	
	
	private Map<String, ArrayList<AttributeS>> map = new HashMap<String,ArrayList<AttributeS>>();

	public void getModel(){
		String model ="";
		for(Map.Entry<String,ArrayList<AttributeS>> entry : map.entrySet()){
			System.out.println(entry.getKey());
			for(AttributeS attr:entry.getValue()){
				System.out.println(attr);
			}
			System.out.println();
		}
	}
	
	public void precdite()throws Exception{
		if(!isModelGenerted) throw new Exception("Haven't Generaten The PredicatedModel");
		if(!isSetTestSet) throw new Exception("Haven't set the Test File");
		
		//TODO precdite;
		double max =0;
		BufferedReader reader = new BufferedReader(new FileReader(testSet));;
		
		String line  = "";
		while((line = reader.readLine()) != null){
			String[] tokens = line.split(" ");
			//Map<String, ArrayList<AttributeS>> map = new HashMap<String,ArrayList<AttributeS>>();
//			System.out.println(Arrays.toString(tokens));
//			System.out.println();
			
			int sum =0;
			for(Map.Entry<String,ArrayList<AttributeS>> entry:map.entrySet()){
				sum +=entry.getValue().size(); 
				double temp = 1;
				double temp_1;
				for(int i =0;i<tokens.length;i++){
					temp_1 = entry.getValue().get(i).getP(tokens[i]);
					System.out.print(temp_1 + " * ");
					temp *= entry.getValue().get(i).getP(tokens[i]);
				}
				System.out.println();
				System.out.println(mapProba.get(entry.getKey()));
				mapProba.put(entry.getKey(),temp * mapProba.get(entry.getKey()) / total_trainNum);
			}
//			System.out.println("sum:" + sum);
//			for(Map.Entry<String,ArrayList<AttributeS>> entry:map.entrySet()){
////				mapProba.put(entry.getKey(),);
//				double temp = entry.getValue().size() * 1.0 / sum;
//				System.out.println("============" + temp);
//				mapProba.put(entry.getKey(),mapProba.get(entry.getKey()) *temp );
//			}
//			System.out.println(line);
//			System.out.println("===================");
			for(Map.Entry<String,Double> entry:mapProba.entrySet()){
				System.out.println(entry.getKey() + " :\t"+entry.getValue());
			}
			System.out.println("===================");
			System.out.println();
		}
	}
	
	
	
	private void addToMap(String line) throws Exception{
//		System.err.println("add to map : " + line);
		String[] temp = line.split(" ");
		if(num == -1) num = temp.length;
		if(num != temp.length) throw new Exception("the file has some error  :" + line);
		int tempNum = num-1;
		int i =0;
		ArrayList<AttributeS> list =null;
		total_trainNum++;
		if(!mapProba.containsKey(temp[tempNum])) mapProba.put(temp[tempNum],(double) 1);
		else mapProba.put(temp[tempNum],mapProba.get(temp[tempNum]) + 1);
		if(!map.containsKey(temp[tempNum])) {
			list = new ArrayList<AttributeS>();
			for(i = 0;i < tempNum;i++){
				try{
					
					Integer.parseInt(temp[i]);
					list.add(new NumericalAttr());
				}catch(Exception e){
					list.add(new CategoricalAttr());
				}
			}
			map.put(temp[tempNum], list);
		}// end if
		list = map.get(temp[tempNum]);
		for(i =0;i<tempNum;i++){
			list.get(i).add(temp[i]);
			map.put(temp[tempNum],list);
		}
			
		}
	}
	
	
	/*�����������ģʽ����һ�ε��õ�ʱ����ʵ����*/
	abstract class AttributeS{
		
		abstract void add(String attr) throws Exception;
		
		abstract double getP(String attribute) throws Exception;
	}
	
	class CategoricalAttr extends AttributeS{
		
		int sum =0;
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		

		void add(String attr){
			sum++;
			if(!map.containsKey(attr)) map.put(attr,1);
			else map.put(attr,map.get(attr)+1);
			
		}
		
		double getP(String attribute) throws Exception{
			double result = 0;
			try{
				result = map.get(attribute) * 1.0 / sum;
			}catch(Exception e){
				throw new Exception("Can't find the attribute : " + attribute);
			}
			return result;
		}
		
		@Override
		public String toString() {
			
			for(Map.Entry<String,Integer> entry:map.entrySet()){
				System.out.print("<" + entry.getKey() + "," + entry.getValue()+">,");
			}
			return "";
		}
	}
	
	class NumericalAttr extends AttributeS{
		int sum;
		int n;
		double average;
		double vaiance;//����
		double vaiance_standard;//��׼��
		double temp_xi =0;
		List<Integer> list = new ArrayList<Integer>();
		boolean isComputed = false;
		

		
		void add(String attr) throws Exception{
			if(isComputed) throw new Exception("the model has been Computed");
			try{
				Integer temp = Integer.parseInt(attr);
				list.add(temp);
				temp_xi += temp * temp;
				sum+=temp;
			}catch(Exception e){
				throw new Exception("Some error in attribute");
			}
		}
		
		
		double getP(String attribute){
			//TODO
			if(isComputed){
				compute();
				isComputed = true;
			}
			int temp = Integer.parseInt(attribute);
			return Math.log(-(Math.pow((temp - average),2)) / (2 * vaiance)) / (vaiance_standard * Math.sqrt(2 * Math.PI));
			
		}
		
		private void compute(){
			average = sum * 1.0/ list.size();
			vaiance = average * average * list.size() + temp_xi - 2 * sum * average;
			vaiance_standard = Math.sqrt(vaiance);
		}
		
		@Override
		public String toString() {
			return list.toString();
		}
	}
	

