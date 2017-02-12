package cs542.lsr.project.lsrAlgo;

import java.io.BufferedReader;
import java.io.FileReader;

public class LsrRoutingAlgo {
	private String fileContent;
	private BufferedReader bReader, bReaderInternal;

	public String lineValues[];
	public String traverseLine;
	public String traverseLine1;

	public int[][] backupCostLSR;
	public int[][] costLSR;
	public int[][] tempCostLSR;

	public static int sourceNode;
	public static int destinationNode;
	public static int[][] nextHop;

	FileReader inputFileReader;
	public int routerCount, row, column;
	public StringBuffer bufferToDisplay;
	
	public int[] checkAgainNodes;

/*
 * Accepting Topology File path as a string to open and read its content	
 */
	public LsrRoutingAlgo(String fileContent) throws Exception {
		this.fileContent = fileContent;
		routerCount = 0;
		inputFileReader = new FileReader(this.fileContent);
		displayTopology(inputFileReader);
	}

/*
 * Checks if input topology is symmetric
 * Populates cost matrix based on input file
 */
	private void displayTopology(FileReader inputFileReader) throws Exception{
			int i, j, k;
			int rowCount = 0;
			bReader = new BufferedReader(inputFileReader);
			if (bReader == null){ 
										//	No proper input specified
			}else{
			if((traverseLine = (bReader.readLine().trim())).isEmpty()) {
										//	Input is empty
			}else{
	
				String[] cost = traverseLine.split(" ");
				for (i = 0; i<cost.length; i++) {
					if (!cost[i].trim().equals("")) {
						routerCount++;
					}
				}
				
			}
		}
	
		costLSR = new int[routerCount][routerCount];
		backupCostLSR = new int[routerCount][routerCount];
		tempCostLSR = new int[routerCount][routerCount];
		
		bReaderInternal = new BufferedReader(new FileReader(fileContent));
		traverseLine = bReaderInternal.readLine();
		if(traverseLine == null) {
										//	Input is empty	
		}else{
			i=j=k=0;
			while(traverseLine != null)
			{	
				rowCount = 0;
				lineValues = traverseLine.split(" ");
				for (i = 0; i<lineValues.length; i++) {
					if (!lineValues[i].trim().equals("")) {
						rowCount++;
					}
				}
	
				if(rowCount == routerCount){
					for(i = 0; i<lineValues.length; i++){
						if(!lineValues[i].equals("")){
							String temp1;
							temp1 = lineValues[i];
							costLSR[j][k] = Integer.parseInt(temp1);
							tempCostLSR[j][k] = Integer.parseInt(temp1);
							backupCostLSR[j][k] = Integer.parseInt(temp1);
							k++;
						}
					}
					k = 0;
					j++;
					traverseLine = bReaderInternal.readLine();
		
				}else{
										//	Input cost matrix is not symmetric			
				}
	
			}
		
		}
	
	}


/*
 * Stores cost matrix to be returned as string for display to the user
 */
	public String costLSRToString() {
		
		bufferToDisplay = new StringBuffer();
		bufferToDisplay.append("\n"+"Input Topology ->"+"\n\n\n");

		bufferToDisplay.append("      R"+(row+1));

		for(row = 1; row< routerCount; row++){
			bufferToDisplay.append("   R"+(row+1));
		}

		bufferToDisplay.append("\n\n");
		
		for (row = 0; row < routerCount; row++) {
			if(row+1 >= 10)
			{
				bufferToDisplay.append("R"+(row+1)+"  ");
				
			}else{
				bufferToDisplay.append("R"+(row+1)+"   ");
			}
			
			for (column = 0; column < routerCount; column++) {
				bufferToDisplay.append(costLSR[row][column]+"   ");
				
			}
			bufferToDisplay.append("\n");
		}

		return bufferToDisplay.toString();
		
	}

/*
 * Calculates smallest distance from source to destination based on link weights(costs)
 * Stores it to be returned as a string to display to user	
 */
	public String findShortestDistance(int source, int destination, int[][] nextHop) {
		int sourceNode = source - 1;
		int destinationNode = destination - 1;
		int i, j = 0;
		StringBuffer path = new StringBuffer();
		checkAgainNodes = new int[routerCount];
		for(i = 0; i<routerCount; i++)
			checkAgainNodes[i] = -1;
		if (destinationNode != sourceNode
				&& destinationNode + 1 <= routerCount) {

			for (i = routerCount - 1; i >= 0; i--) {
				if (nextHop[destinationNode][i] != 0) {
					path.append(nextHop[destinationNode][i] + "=>");
					checkAgainNodes[j] = nextHop[destinationNode][i];
					j++;
				}

			}
			path.append(Integer.toString(destinationNode + 1));
			checkAgainNodes[j] = destinationNode+1;
		}
		return path.toString();

	}

}
