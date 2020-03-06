
import java.util.*;
import java.io.IOException;

class main{
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int maxX=8,maxY=8, bombs=10;
		System.out.print("Number of rows: ");maxY=sc.nextInt();
		System.out.print("Number of collumns: ");maxX=sc.nextInt();
		System.out.print("Number of mines: "); bombs=sc.nextInt();
		clrscr();
		maxY-=1;
		maxX-=1;
		int table[][]=new int[maxX+1][maxY+1];
		int map[][]=new int[maxX+1][maxY+1];
		int hidemap[][]=new int[maxX+1][maxY+1];
		int bombplace[][]=new int[maxX+1][maxY+1];
		int x,y,c;
		
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				table[i][j]=0;
			}
		}
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				bombplace[i][j]=0;
			}
		}
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				map[i][j]=0;
			}
		}
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				hidemap[i][j]=0;
			}
		}
	
		
		for(int i=0;i<bombs;i++){
		int bombX=-1;
		int bombY=-1;
		boolean go=true;
		do{
		go=true;
		bombX =getRandomIntBetweenRange(0,maxX);
		bombY=getRandomIntBetweenRange(0,maxY);
		
		bombplace[bombX][bombY]=1;
		//bombplace[1][1]=1;
		
		for(int ii=0;ii<maxX;ii++){
			for(int jj=0;jj<maxY;jj++){
				if(bombplace[ii][jj]==1&&table[bombX][bombY]==2)go=false;
			}
		}
		
		table[bombX][bombY]=2;
		//table[1][1]=2;
		
		// System.out.println(bombX+":"+bombY);
		}while(!go);
		}
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				if(table[i][j]!=2){
					int bombn=ScanBomb(i,j,table,maxX,maxY);
					if(bombn!=0){
						//System.out.print(bombn+" ");
						map[i][j]=bombn;
					}
					else{
						//System.out.print("  ");
						map[i][j]=0;
					}
				}
				else{
					//System.out.print("M ");
					map[i][j]=9;
				}
			}
			//System.out.println();
		}
		//System.out.println();
		
		while(true){
		
		
		// for(int i=0;i<maxX+1;i++){
			// for(int j=0;j<maxY+1;j++){
				// if(map[i][j]==9)
					// System.out.print("M ");
				// else if(map[i][j]==0)
					// System.out.print("  ");
				// else
				// System.out.print(map[i][j]+" ");	
			// }
			
			// System.out.println();
		// }
		// System.out.println();
		
		
		// for(int i=0;i<maxX+1;i++){
			// for(int j=0;j<maxY+1;j++){
			// //	System.out.print(table[i][j]);
				// if(table[i][j]==0)
					// System.out.print(". ");
				// else if(table[i][j]==1)
					// System.out.print("o ");
				// else if(table[i][j]==2)
				    // System.out.print("! ");
			// }
			// System.out.println();
		// }
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				if(hidemap[i][j]==0){
				System.out.print("[]");
				}
				else if(hidemap[i][j]==2){
					System.out.print("F ");
				}
				else if(hidemap[i][j]==1){
				System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
		
	    System.out.println();
		
		System.out.print("X: ");
		x=sc.nextInt();
		System.out.print("Y: ");
		y=sc.nextInt();
		System.out.print("Flag/UnFlag 1, Dig 2: ");
		c=sc.nextInt();
		if(c!=2)c=2;
		else if(c==2)c=1;

		x=clamp(x,maxX);
		y=clamp(y,maxY);
		
       
		
		System.out.println("\n\n");
		clrscr();
		
		if(c!=2){
		if(map[x][y]!=9){
			hidemap[x][y]=1;
		}
		else if(map[x][y]==9){
			break;
		}
		}else if(c==2){
			if(hidemap[x][y]==0)
			hidemap[x][y]=2;
			else {
				if(hidemap[x][y]!=1)
				hidemap[x][y]=0;
			}
		}
		
	}
	
		
		for(int i=0;i<maxX+1;i++){
			for(int j=0;j<maxY+1;j++){
				if(map[i][j]==9){
				hidemap[i][j]=1;
				}
			}
		}
		
		for(int i=0;i<maxX+1;i++){
		for(int j=0;j<maxY+1;j++){
			if(hidemap[i][j]==0){
			System.out.print("[]");
			}
			else if(hidemap[i][j]==1){
			if(map[i][j]!=9)
			System.out.print(map[i][j]+" ");
			else if(map[i][j]==9){
				System.out.print("M ");
			}
			}
		    if(hidemap[i][j]==2){
				if(map[i][j]!=9)
				System.out.print("X ");
				else if(map[i][j]==9)
				System.out.print("F ");
			}
		}
		System.out.println();
		}
		
		System.out.println("\nYou Lose\n");
		
	}
	public static int ScanBomb(int a,int b,int table[][],int maxX,int maxY){
		int bombNumber=0;
		//int a=2;int b=2;
		int ap=a+1,bp=b+1;
		int am=a-1,bm=b-1;
		//table[a][b]=1;
		if(ap<maxY){
	    if(table[ap][b]==2)bombNumber++;
	    if(bp<maxX)
	    if(table[ap][bp]==2)bombNumber++;
	    if(bm>=0)
	    if(table[ap][bm]==2)bombNumber++;
		}
		if(am>=0){
		if(table[am][b]==2)bombNumber++;
		if(bp<maxX)
	    if(table[am][bp]==2)bombNumber++;
	    if(bm>=0)
	    if(table[am][bm]==2)bombNumber++;
		}
		if(bp<maxX)
		if(table[a][bp]==2)bombNumber++;
		if(bm>=0)
		if(table[a][bm]==2)bombNumber++;
		return bombNumber;
	}
	public static int clamp(int x, int max){
		return x>max?max:x;
	}
	public static int getRandomIntBetweenRange(int min, int max){
		Random random = new Random();
		int randomInteger = random.nextInt(max);
		return randomInteger;
	}
	public static void clrscr(){
		//Clears Screen in java
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {}
	} 
}